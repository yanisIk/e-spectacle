package ca.etsmtl.gti525.espectacles.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;
import ca.etsmtl.gti525.espectacles.LoggerMessageConstants;
import ca.etsmtl.gti525.espectacles.domain.Produit;
import ca.etsmtl.gti525.espectacles.domain.Representation;
import ca.etsmtl.gti525.espectacles.repositories.RepresentationRepository;


@Service
public class RepresentationService {

	final private Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class);
	
	@Autowired
	private RepresentationRepository representationRepo;
	
	public List<Representation> getAllRepresentations(){
	    logger.info(LoggerMessageConstants.REPRESENTATION_GET_ALL);
	    return representationRepo.findAll();
	}

	public Representation getRepresentation(Long productId) {
	    logger.info(LoggerMessageConstants.REPRESENTATION_GET_WITH_PRODUCT_ID + productId);
	    return representationRepo.findOne(productId);
	}

	public Representation findByName(String name) {
	    logger.info(LoggerMessageConstants.REPRESENTATION_GET_WITH_NAME + name);
	    return representationRepo.findByNom(name);
	}

	
}
