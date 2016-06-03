package ca.etsmtl.gti525.espectacle.jobs;

import java.util.Date;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;
import ca.etsmtl.gti525.espectacles.LoggerMessageConstants;
import ca.etsmtl.gti525.espectacles.domain.Panier;
import ca.etsmtl.gti525.espectacles.services.PanierService;

@Component
public class PanierJobs {

	public static final int EXPIRY_DELAY = 10*60*1000;
	final private static Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class); 
	
	public static TimerTask getViderPanier20mnJob(HttpSession session){
		
		return new TimerTask(){
			public void run() {
				Panier panier = (Panier) session.getAttribute("panier");
				if(panier != null){
					WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
				    PanierService panierService = (PanierService)webApplicationContext.getBean(PanierService.class);
					panierService.viderPanier(session);
					logger.info(LoggerMessageConstants.PANIER_VIDER_SESSION_TIMEOUT);
				}
			}
		};
	}
	
	public static synchronized Date getNextDateTime(){
		long nextDateMS = System.currentTimeMillis()+EXPIRY_DELAY;
		Date dateAfter = new Date(nextDateMS);
		return dateAfter;
	}
}
