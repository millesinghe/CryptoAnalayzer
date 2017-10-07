package com.cmcpredict.operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cmcpredict.helper.PropertyHandler;

public class CurrencyData {

	public String getConversionRate(String from, String to) throws IOException {
		String url = "http://download.finance.yahoo.com/d/quotes.csv?s=" + from + to + "=X&f=l1&e=.csv";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		return response.toString();

	}

	public JSONArray requestTOCMCServer(String strURL) {

		JSONArray jsonResult = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(strURL);
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				System.out.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				return null;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output = "";
			String newOutput;
			while ((newOutput = br.readLine()) != null) {
				output = output + newOutput;
			}
			jsonResult = new JSONArray(output);
			httpClient.getConnectionManager().shutdown();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	public JSONArray getCoinInfo(String coinID) {

		PropertyHandler coinProp = new PropertyHandler();
		String coinName = coinProp.readCoin(coinID);
		String strURL = null;
		if (coinID.equals("")) {
			strURL = "https://api.coinmarketcap.com/v1/ticker/";
		}else {
			strURL = "https://api.coinmarketcap.com/v1/ticker/" + coinName+"/";
		}		
		return this.requestTOCMCServer(strURL);
	}

}
