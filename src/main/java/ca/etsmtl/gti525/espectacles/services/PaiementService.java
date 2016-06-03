package ca.etsmtl.gti525.espectacles.services;

import gti525.paiement.IPaiementDAO;
import gti525.paiement.InformationsPaiementTO;
import gti525.paiement.ReponseSystemePaiementTO;
import gti525.paiement.RequeteAuthorisationTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;
import ca.etsmtl.gti525.espectacles.utility.ESpectacleJsonParser;
import ca.etsmtl.gti525.espectacles.utility.HttpCall;
import ca.etsmtl.gti525.espectacles.utility.PaiementTransferObjectUtil;

@Service
public class PaiementService implements IPaiementDAO {

	final private Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class);

	@Override
	public ReponseSystemePaiementTO approuverTransaction(RequeteAuthorisationTO infosTransaction) {

		String url = "http://gti525.herokuapp.com/transactions/" + infosTransaction.getTransaction_id() + ".json";
		String urlParams = PaiementTransferObjectUtil.infoTransactionToUrlParams(infosTransaction);
		String jsonResponse = HttpCall.executeGet(url + "?" + urlParams);

		logger.info("This is the response from the paiement API for FINAL authorization : " + jsonResponse);

		return ESpectacleJsonParser.craftResponseWithJSONResponse(jsonResponse);
	}

	@Override
	public ReponseSystemePaiementTO effectuerPreauthorisation(InformationsPaiementTO infosPaiement) {

		String urlParams = PaiementTransferObjectUtil.infosPaiementToUrlParams(infosPaiement);
		String jsonResponse = HttpCall.executePost("http://gti525.herokuapp.com/transactions.json", urlParams);

		logger.info("This is the response from the paiement API for preauthorization : " + jsonResponse);

		return ESpectacleJsonParser.craftResponseWithJSONResponse(jsonResponse);
	}
}
