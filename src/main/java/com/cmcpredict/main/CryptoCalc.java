package com.cmcpredict.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONArray;

import com.cmcpredict.helper.ExcelHandler;
import com.cmcpredict.helper.PropertyHandler;
import com.cmcpredict.operation.Coin;
import com.cmcpredict.operation.CurrencyData;

public class CryptoCalc {

	public void requestCoinsData() {
		System.out.println(".............. STARTS DATA RETRIEVING PROCESS ..............");

		String usd2lkr = null;

		try {
			CurrencyData c = new CurrencyData();
			usd2lkr = c.getConversionRate("USD", "LKR");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PropertyHandler prop = new PropertyHandler();
		String[] boughtCoinList = prop.readUserPropery("bought.coins").split(",");

		ArrayList<Coin> coinList = new ArrayList<Coin>();

		for (String boughtCoin : boughtCoinList) {
			Coin coin = new Coin(boughtCoin, usd2lkr);
			coin.processWalletValuation();
			coinList.add(coin);
		}

		System.out.println("============== FINISHED DATA RETRIEVING PROCESS ==============");

		System.out.println(".............. STARTS DATA CALCULATING PROCESS ..............\n");
		for (Coin coin : coinList) {
			coin.calculateValue();
		}

		System.out.println("\n============== FINISHED DATA CALCULATING PROCESS ==============");

		System.out.println(".............. STARTS EXPORTING COINS' DATA STATS ..............\n");
		for (Coin coin : coinList) {
			System.out.println("Exporting " + coin.getCoinID() + " info");
			JSONArray jsonArr = coin.getDataListJSON();
			ExcelHandler excel = new ExcelHandler();
			try {
				excel.writeToExcel(jsonArr);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("\n============== FINISHED EXPORTING COINS' DATA STATS ==============");

	}

	public void startAnalyse(String coinName, String LKR2USD, String coinValue, String amntCoin) {

		System.out.println(coinName + " Valuation in USD => "
				+ Double.parseDouble(amntCoin) * Double.parseDouble(coinValue) + " USD");

		System.out.println(coinName + " Valuation in LKR => "
				+ Double.parseDouble(amntCoin) * Double.parseDouble(coinValue) * Double.parseDouble(LKR2USD) + " LKR");

	}

}
