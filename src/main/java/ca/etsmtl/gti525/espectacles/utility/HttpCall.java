package ca.etsmtl.gti525.espectacles.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Code baser sur le code trouver aux adresses suivante
 * http://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java
 * http://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
 */
public class HttpCall {

	private static final String USER_AGENT = "Mozilla/5.0";

	public static String executeGet(String targetURL) {

		StringBuffer response = new StringBuffer();
		HttpURLConnection connection = null;
		URL url;

		try {
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();

			// optional default is GET
			connection.setRequestMethod("GET");

			// add request header
			connection.setRequestProperty("User-Agent", USER_AGENT);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}

		return response.toString();
	}

	public static String executePost(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return "";

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}

}
