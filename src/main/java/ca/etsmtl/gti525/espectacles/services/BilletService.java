package ca.etsmtl.gti525.espectacles.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;
import ca.etsmtl.gti525.espectacles.LoggerMessageConstants;
import ca.etsmtl.gti525.espectacles.domain.Billet;
import ca.etsmtl.gti525.espectacles.repositories.BilletRepository;

@Service
public class BilletService {

	final private Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class);
	
	@Autowired
	private BilletRepository billetRepo;
	
	
	public Billet findById(Long id){
	    logger.info(LoggerMessageConstants.BILLET_RECHERCHE_PAR_ID + id);
	    return billetRepo.findOne(id);
	}
	
	public Billet findByRepresentationId(Long id){
	    logger.info(LoggerMessageConstants.BILLET_RECHERCHE_PAR_REPRESENTATION_ID + id);
	    return billetRepo.findByRepresentationId(id);
	}
	
	public Billet update(Billet billet){
		if(billet.getId() != null){
		    logger.info(LoggerMessageConstants.BILLET_SAUVEGARDE_REPO + billet.getId());
		    return billetRepo.save(billet);	
		}
		return null;
	}
}
