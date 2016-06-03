package ca.etsmtl.gti525.espectacles;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ca.etsmtl.gti525.espectacle.jobs.PanierJobs;
import ca.etsmtl.gti525.espectacles.domain.Panier;
import ca.etsmtl.gti525.espectacles.services.PanierService;
 
@Component
public class WebSessionListener implements HttpSessionListener {
	
	final private Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class); 
	
    //Notification that a session was created.
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionCreatedEvent) {
    	
      //La tache pour vider le panier est schedule lorsque on ajoute le 1er item
      // et non a la creation de la session.
    }
     
    //Notification that a session is about to be invalidated.
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionDestroyedEvent) {
    	Panier panier = (Panier) httpSessionDestroyedEvent.getSession().getAttribute("panier");
    	WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(httpSessionDestroyedEvent.getSession().getServletContext());
	    PanierService panierService = (PanierService)webApplicationContext.getBean(PanierService.class);
    	panierService.viderPanier(httpSessionDestroyedEvent.getSession());
    	logger.info(LoggerMessageConstants.PANIER_VIDER_SESSION_DESTROYED);
    }
}