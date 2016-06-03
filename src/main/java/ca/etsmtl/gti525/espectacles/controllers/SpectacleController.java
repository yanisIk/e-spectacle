package ca.etsmtl.gti525.espectacles.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;
import ca.etsmtl.gti525.espectacles.LoggerMessageConstants;
import ca.etsmtl.gti525.espectacles.domain.Billet;
import ca.etsmtl.gti525.espectacles.domain.CategoriesSpectacle;
import ca.etsmtl.gti525.espectacles.domain.Representation;
import ca.etsmtl.gti525.espectacles.domain.Spectacle;
import ca.etsmtl.gti525.espectacles.services.BilletService;
import ca.etsmtl.gti525.espectacles.services.RepresentationService;
import ca.etsmtl.gti525.espectacles.services.SpectacleService;

@Controller
@RequestMapping(value="/spectacles")
public class SpectacleController {

	final private Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class);
	
	@Autowired
	private SpectacleService spectacleService;
	@Autowired
	private RepresentationService representationService;
	@Autowired
	private BilletService billetService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getSpectacles(Model model){
		
		List<Spectacle> spectaclesList = spectacleService.getAllSpectacles();
		model.addAttribute("spectaclesList", spectaclesList);
		
		logger.info(LoggerMessageConstants.AJOUT_LISTE_SPECTACLES_SHOP);
		
		return "shop";
	}
	
	@RequestMapping(params="name", method = RequestMethod.GET)
	public String getSpectacleDetails(@RequestParam(value="name", required=true) String name,Model model){
		
		Spectacle spectacle = spectacleService.getSpectacleByName(name);
		Representation representation = spectacle.getRepresentations().get(0);
		Billet billet = billetService.findByRepresentationId(representation.getId());
		model.addAttribute("spectacle", spectacle);
		model.addAttribute("billet", billet);
		
		logger.info(LoggerMessageConstants.AJOUT_BILLER_SPECTACLE_PRODUCT_DETAILS);
		
		return "product-details";
	}
	
	
	/**
	 * Permet de rechercher un spectacle (requetes via ajax)
	 * @param criteria
	 * @return page html avec les spectacles trouvés
	 */
	@RequestMapping(value="/search", params="criteria", method=RequestMethod.GET)
    public String search(@RequestParam(value="criteria", required=true) String criteria, Model model){
		
		List<Spectacle> spectacles = spectacleService.search(criteria);
		model.addAttribute("spectacles", spectacles);
		
		logger.info(LoggerMessageConstants.AJOUT_SPECTACLES_TROUVES_RECHERCHE);
		
		return "products";
	}
    
	
	@RequestMapping(value="/representation" ,params="name", method = RequestMethod.GET)
	public String getRepresentation(@RequestParam(value="name", required=true) String name, Model model){
		
		Representation representation = representationService.findByName(name);
		Billet billet = billetService.findByRepresentationId(representation.getId());
		model.addAttribute("representation", representation);
		model.addAttribute("billet", billet);
		
		logger.info(LoggerMessageConstants.AJOUT_REPRESENTATION_GET_REPRESENTATION);
		
		return "product-details";
	}
	
	
	
	/**
	 * Permet de rechercher des spectacles selon la categorie (requetes via ajax)
	 * @param criteria
	 * @return page html avec les spectacles trouvés
	 */
	@RequestMapping(params="category", method=RequestMethod.GET)
    public String getByCategory(@RequestParam(value="category", required=true) String category, Model model){
		CategoriesSpectacle categoryEnum = CategoriesSpectacle.valueOf(category);
		List<Spectacle> spectacles = spectacleService.findByCategory(categoryEnum);
		model.addAttribute("spectaclesList", spectacles);
		
		logger.info(LoggerMessageConstants.AJOUT_LISTE_SPECTACLE_CATEGORIE);
		
		return "shop";
	}
	
}
