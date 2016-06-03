package ca.etsmtl.gti525.espectacles.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;
import ca.etsmtl.gti525.espectacles.LoggerMessageConstants;
import ca.etsmtl.gti525.espectacles.domain.Billet;
import ca.etsmtl.gti525.espectacles.domain.Panier;
import ca.etsmtl.gti525.espectacles.domain.Produit;
import ca.etsmtl.gti525.espectacles.domain.ProduitCommande;
import ca.etsmtl.gti525.espectacles.services.BilletService;
import ca.etsmtl.gti525.espectacles.services.PanierService;
import ca.etsmtl.gti525.espectacles.services.RepresentationService;


@Controller
@Scope("session")
@RequestMapping(value="/panier")
public class PanierController {

	
	final private Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class);
	
	//Inject session scoped bean
	@Autowired
	private Panier panier;
	@Autowired
	private BilletService billetService;
	@Autowired
	private PanierService panierService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String consultCart(Model model){
		//Panier is already accessible in session from JSP, no need to add it as attribute
	    	logger.info(LoggerMessageConstants.CONSULTER_PANIER);
		return "cart";
	}

	
	@RequestMapping(value = "/add", params={"productId", "quantity"}, method = RequestMethod.GET)
	public String add(Model model, @RequestParam(value="productId", required=true) Long productId ,
			@RequestParam(value="quantity") Integer quantity, HttpSession session){
		
		//Add session id to panier if not already set
		if(panier.getSessionId() == null){
			panier.setSessionId(session.getId());
		}
		//Planifier vidage lorsque 1er item ajoute
		if(panier.getProducts().isEmpty()){
			panierService.planifierVidagePanier(session);
		}
		
		Produit product = billetService.findByRepresentationId(productId);
		if(product != null){
			if(quantity == null) quantity = 1;
			panier.addProduct(product, quantity);
			((Billet) product).decrementerQuantite(quantity);
			billetService.update((Billet) product);
			logger.info(constructCartProductLogMessage(LoggerMessageConstants.AJOUT_PRODUIT_PANIER_DECREMENTER_QUANTITE_BILLET, quantity, product));
		}
		
		return "cartIcon";
	}

	
	
	@RequestMapping(value = "/increment", params={"productId"}, method = RequestMethod.GET)
	public String increment(Model model, @RequestParam(value="productId", required=true) Long productId){
				
		Set<ProduitCommande> products = panier.getProducts();
		for(ProduitCommande produit : products){
			if (((Billet) produit.getProduit()).getRepresentation().getId().equals(productId)){
				produit.incrementQuantityBy(1);
				((Billet) produit.getProduit()).decrementerQuantite(1);
				billetService.update((Billet) produit.getProduit());
				logger.info(constructCartProductLogMessage(LoggerMessageConstants.AJOUT_PRODUIT_PANIER_DECREMENTER_QUANTITE_BILLET, 1, produit.getProduit()));
				break;
			}
		}
		
		return "cart";
	}
	
	@RequestMapping(value = "/decrement", params={"productId"}, method = RequestMethod.GET)
	public String decrement(Model model, @RequestParam(value="productId", required=true) Long productId){
				
		Set<ProduitCommande> products = panier.getProducts();
		for(ProduitCommande produit : products){
			if (produit.getProduit().getId().equals(productId)){
				produit.incrementQuantityBy(-1);
				((Billet) produit.getProduit()).incrementerQuantite(1);
				billetService.update((Billet) produit.getProduit());
				logger.info(constructCartProductLogMessage(LoggerMessageConstants.ENLEVE_PRODUIT_PANIER_INCREMENTE_QUANTITE_BILLET,
					1, produit.getProduit()));
				break;
			}
		}
		
		return "cart";
	}
	
	@RequestMapping(value = "/remove", params={"productId"}, method = RequestMethod.GET)
	public String remove(Model model, @RequestParam(value="productId", required=true) Long productId){
				
		ProduitCommande productToRemove = null;
		
		Set<ProduitCommande> products = panier.getProducts();
		for(ProduitCommande produit : products){
			if (produit.getProduit().getId().equals(productId)){
				productToRemove = produit;
				break;
			}
		}
		if(productToRemove != null){
			products.remove(productToRemove);
			((Billet) productToRemove.getProduit()).incrementerQuantite(productToRemove.getQuantite());
			billetService.update((Billet) productToRemove.getProduit());
			logger.info(constructCartProductLogMessage(LoggerMessageConstants.ENLEVE_PRODUIT_PANIER_INCREMENTE_QUANTITE_BILLET,
				productToRemove.getQuantite(), productToRemove.getProduit()));
		}
		
		return "cart";
	}
	
	private String constructCartProductLogMessage(String initialMessage, Integer quantity,
		Produit product) {
	    StringBuffer stringBuffer = new StringBuffer(initialMessage);
	    stringBuffer.append(product.getNom());
	    stringBuffer.append(" (");
	    stringBuffer.append(quantity);
	    stringBuffer.append(").");
	    return stringBuffer.toString();
	}
	
}
