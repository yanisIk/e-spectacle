package ca.etsmtl.gti525.espectacles.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;
import ca.etsmtl.gti525.espectacles.LoggerMessageConstants;
import ca.etsmtl.gti525.espectacles.domain.Spectacle;
import ca.etsmtl.gti525.espectacles.services.SpectacleService;

@Controller
public class HomepageController {

	final private Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class);
	
	@Autowired
	private SpectacleService spectacleService;
	
	@RequestMapping(value = "/")
	public String welcome(Model model){
		//Liste des spectacles 'a l'afffiche
		List<Spectacle> spectaclesList = spectacleService.getFeaturedSpectacles();
		model.addAttribute("spectaclesList", spectaclesList);
		logger.info(LoggerMessageConstants.AJOUT_LISTE_SPECTACLES_INDEX);
		return "index";
	}
}
