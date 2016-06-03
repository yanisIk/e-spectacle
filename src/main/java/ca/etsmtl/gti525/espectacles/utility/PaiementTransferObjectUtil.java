package ca.etsmtl.gti525.espectacles.utility;

import gti525.paiement.InformationsPaiementTO;
import gti525.paiement.ReponseSystemePaiementTO;
import gti525.paiement.RequeteAuthorisationTO;

import java.math.BigDecimal;

import ca.etsmtl.gti525.espectacles.domain.Paiement;

public class PaiementTransferObjectUtil {

	private static final String PAIEMENT_API_KEY = "00e45953c30264a28e08";
	private static final int STORE_ID = 69;

	public static InformationsPaiementTO populateInfosPaiement(String prenom, String nom, String creditCard,
			Paiement paiement, long factureId) {
		InformationsPaiementTO infosPaiement = new InformationsPaiementTO();
		infosPaiement.setApi_key(PAIEMENT_API_KEY);

		infosPaiement.setFirst_name(prenom);
		infosPaiement.setLast_name(nom);
		infosPaiement.setCard_number(Long.parseLong(creditCard));

		// TODO : Remove Hardcoded values
		infosPaiement.setOrder_id(factureId);
		infosPaiement.setStore_id(STORE_ID);
		infosPaiement.setSecurity_code(111);
		infosPaiement.setYear(2016);
		infosPaiement.setMonth(01);
		infosPaiement.setAmount(new BigDecimal(paiement.getPrice()));

		return infosPaiement;
	}

	public static RequeteAuthorisationTO populateInfosTransaction(ReponseSystemePaiementTO reponsePreAuth) {
		RequeteAuthorisationTO infosTransaction = new RequeteAuthorisationTO();
		infosTransaction.setApi_key(PAIEMENT_API_KEY);
		infosTransaction.setStore_id(STORE_ID);
		infosTransaction.setTransaction_id(reponsePreAuth.getTransactionId());
		return infosTransaction;
	}

	public static String infoTransactionToUrlParams(RequeteAuthorisationTO infosTransaction) {

		StringBuffer sb = new StringBuffer();

		sb.append("api_key=");
		sb.append(infosTransaction.getApi_key());
		sb.append("&");

		sb.append("store_id=");
		sb.append(infosTransaction.getStore_id());
		sb.append("&");

		sb.append("transaction_id=");
		sb.append(infosTransaction.getTransaction_id());

		return sb.toString();
	}

	public static String infosPaiementToUrlParams(InformationsPaiementTO infosPaiement) {

		StringBuffer sb = new StringBuffer();

		sb.append("api_key=");
		sb.append(infosPaiement.getApi_key());
		sb.append("&");

		sb.append("store_id=");
		sb.append(infosPaiement.getStore_id());
		sb.append("&");

		sb.append("order_id=");
		sb.append(infosPaiement.getOrder_id());
		sb.append("&");

		sb.append("first_name=");
		sb.append(infosPaiement.getFirst_name());
		sb.append("&");

		sb.append("last_name=");
		sb.append(infosPaiement.getLast_name());
		sb.append("&");

		sb.append("card_number=");
		sb.append(infosPaiement.getCard_number());
		sb.append("&");

		sb.append("security_code=");
		sb.append(infosPaiement.getSecurity_code());
		sb.append("&");

		sb.append("year=");
		sb.append(infosPaiement.getYear());
		sb.append("&");

		sb.append("month=");
		sb.append(infosPaiement.getMonth());
		sb.append("&");

		sb.append("amount=");
		sb.append(infosPaiement.getAmount());

		return sb.toString();
	}
}
