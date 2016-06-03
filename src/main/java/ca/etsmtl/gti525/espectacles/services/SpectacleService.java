package ca.etsmtl.gti525.espectacles.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;
import ca.etsmtl.gti525.espectacles.LoggerMessageConstants;
import ca.etsmtl.gti525.espectacles.domain.CategoriesSpectacle;
import ca.etsmtl.gti525.espectacles.domain.Spectacle;
import ca.etsmtl.gti525.espectacles.repositories.SpectacleRepository;

@Service
public class SpectacleService {

	final private Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class);
	
	@Autowired
	private SpectacleRepository spectacleRepo;
	
	public List<Spectacle> getAllSpectacles(){
	    logger.info(LoggerMessageConstants.SPECTACLES_GET_ALL);
	    return spectacleRepo.findAll();
	}
	
	public List<Spectacle> search(String attribut){
	    return spectacleRepo.findByDescriptionContainingOrNomContainingAllIgnoreCase(attribut, attribut);
	}

	public Spectacle getSpectacleByName(String name) {
	    logger.info(LoggerMessageConstants.SPECTACLES_GET_WITH_SPECTACLE_NAME + name);
	    return spectacleRepo.findByNom(name);
	}
	
	public List<Spectacle> getFeaturedSpectacles(){
	    logger.info(LoggerMessageConstants.SPECTACLES_GET_FEATURED);
	    PageRequest page = new PageRequest(0,3, Direction.ASC, "nom");
	    return spectacleRepo.findAll(page).getContent();
	}

	public List<Spectacle> findByCategory(CategoriesSpectacle category) {
	    logger.info(LoggerMessageConstants.SPECTACLES_GET_BY_CATEGORY + category.toString());
	    return spectacleRepo.findByCategorie(category);
	}
	
}
