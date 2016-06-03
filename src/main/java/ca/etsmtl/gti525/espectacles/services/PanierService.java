package ca.etsmtl.gti525.espectacles.services;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import ca.etsmtl.gti525.espectacle.jobs.PanierJobs;
import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;
import ca.etsmtl.gti525.espectacles.LoggerMessageConstants;
import ca.etsmtl.gti525.espectacles.domain.Billet;
import ca.etsmtl.gti525.espectacles.domain.Panier;
import ca.etsmtl.gti525.espectacles.domain.Produit;
import ca.etsmtl.gti525.espectacles.domain.ProduitCommande;

@Service
public class PanierService {
	
	final private Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class);
	
	@Autowired
	BilletService billetService;
	
	public void viderPanier(HttpSession session){

		Panier panier = (Panier) session.getAttribute("panier");
		Set<ProduitCommande> products = panier.getProducts();
		for(ProduitCommande produitCommande : products){
			Produit billet = produitCommande.getProduit();
			int quantite = produitCommande.getQuantite();
			((Billet) billet).incrementerQuantite(quantite);
			billetService.update((Billet) billet);
		}
		panier.getProducts().clear();
		session.setAttribute("panier", panier);
	}
	
	public void planifierVidagePanier(HttpSession session){
		TimerTask viderPanier20mnJob = PanierJobs.getViderPanier20mnJob(session);
		Timer timer = new Timer();
		timer.schedule(viderPanier20mnJob, PanierJobs.getNextDateTime());
		logger.info(LoggerMessageConstants.TIMER_SESSION_INIT);
	}
}
