package ca.etsmtl.gti525.espectacles.controllers;

import gti525.paiement.InformationsPaiementTO;
import gti525.paiement.ReponseSystemePaiementTO;
import gti525.paiement.RequeteAuthorisationTO;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ca.etsmtl.gti525.espectacles.domain.Facture;
import ca.etsmtl.gti525.espectacles.domain.Guest;
import ca.etsmtl.gti525.espectacles.domain.Paiement;
import ca.etsmtl.gti525.espectacles.domain.Panier;
import ca.etsmtl.gti525.espectacles.services.PaiementService;
import ca.etsmtl.gti525.espectacles.services.PanierService;
import ca.etsmtl.gti525.espectacles.utility.PaiementTransferObjectUtil;

@Controller
@Scope("session")
@RequestMapping(value = "/panier/checkout")
public class CheckoutController {

	// Inject session scoped bean
	@Autowired
	private Panier panier;
	@Autowired
	private Facture facture;
	@Autowired
	private PanierService panierService;
	@Autowired
	private PaiementService paiementService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String checkout(Model model) {

		// Si facture n'est pas initialisee encore
		if (facture.getProduitsCommandes().isEmpty()) {
			facture.getProduitsCommandes().addAll(panier.getProducts());
		}

		return "checkout";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String payment(Model model, HttpServletRequest request, @RequestParam("prenom") String prenom,
			@RequestParam("nom") String nom, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("postalCode") String postalCode,
			@RequestParam("country") String country, @RequestParam("creditCard") String creditCard,
			@RequestParam("expDate") String expDate) {

		// validate input data

		// if error, redirect to checkout with errors

		// else add paiement to facture, empty cart and return sales-details
		Guest guest = new Guest();
		guest.setNom(nom + ", " + prenom);
		guest.setEmail(email);
		guest.setAddresseFacturation(address + " " + country + " " + postalCode);
		guest.setAddresseLivraison(address + " " + country + " " + postalCode);

		Paiement paiement = new Paiement(facture, creditCard, expDate);

		facture.setGuest(guest);
		facture.setPaiement(paiement);

		// Build InformationsPaiementTO object

		if (nom.equals("") || prenom.equals("") || creditCard.equals("")) {
			model.addAttribute("messageErreurChamp", "Please fill all the required fields correctly.");
			return "checkout";
		}

		if (creditCard.length() != 16) {
			model.addAttribute("messageErreurChamp", "Credit card number must be 16 numbers long.");
			return "checkout";
		}

		// TODO : Numero commande hardcoder
		InformationsPaiementTO infosPaiement = PaiementTransferObjectUtil.populateInfosPaiement(prenom, nom,
				creditCard, paiement, 1000);
		ReponseSystemePaiementTO reponsePreAuth = paiementService.effectuerPreauthorisation(infosPaiement);
		model.addAttribute("messagePaiement", reponsePreAuth.getMessage());

		if (reponsePreAuth.getStatus().equals("Accepted")) { // If code
																// accepted
			// Vider panier
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getRequiredWebApplicationContext(request.getSession().getServletContext());
			PanierService panierService = (PanierService) webApplicationContext.getBean(PanierService.class);
			panierService.viderPanier(request.getSession());

			// Generer numero de commande
			facture.setNumeroCommande(UUID.randomUUID().toString());

			// Sauvegarder facture et paiement

			RequeteAuthorisationTO infosTransaction = PaiementTransferObjectUtil
					.populateInfosTransaction(reponsePreAuth);
			ReponseSystemePaiementTO responseFinalAuth = paiementService.approuverTransaction(infosTransaction);
			model.addAttribute("messagePaiement", responseFinalAuth.getMessage());

			return "sales-details";
		} else {

			return "checkout";
		}

	}
}
