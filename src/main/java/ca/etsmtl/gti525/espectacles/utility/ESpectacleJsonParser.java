package ca.etsmtl.gti525.espectacles.utility;

import gti525.paiement.ReponseSystemePaiementTO;

import org.json.JSONObject;

public class ESpectacleJsonParser {

	public static ReponseSystemePaiementTO craftResponseWithJSONResponse(String jsonResponse) {

		JSONObject jsonObject = new JSONObject(jsonResponse);
		JSONObject orderObject = jsonObject.getJSONObject("order");

		ReponseSystemePaiementTO reponse = new ReponseSystemePaiementTO();

		reponse.setCode(orderObject.getInt("code"));
		reponse.setStatus(orderObject.getString("status"));
		reponse.setMessage(orderObject.getString("messages"));

		// Avoid JSONObject not found
		if (reponse.getStatus().equals("Accepted")) {
			reponse.setTransactionId(orderObject.getInt("transaction_id"));
		}

		return reponse;
	}
}
