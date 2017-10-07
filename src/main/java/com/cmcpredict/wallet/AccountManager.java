package com.cmcpredict.wallet;

import java.io.IOException;

import org.json.JSONArray;

import com.cmcpredict.helper.SystemHelper;
import com.cmcpredict.operation.CurrencyData;

public class AccountManager {

	private double investLKR;

	public void analyzeTransaction() {

	}

	public void analyzeProfitMargin() {

	}

	public void analyzeSituation() {

	}

	public void bestInvestment(String coinId) {

	}

	public void proceedAction() {
		SystemHelper ops = new SystemHelper();

		String question = "Investment Amount (LKR) :";
		String result = ops.getInput(question).toString();
		this.investLKR = Double.parseDouble(result);

		CurrencyData c = new CurrencyData();
		try {
			String usd2lkr = c.getConversionRate("USD", "LKR");
			double availableUSDs = this.investLKR / Double.parseDouble(usd2lkr);
			System.out.println(" >> Receiving USD - " + availableUSDs);

			JSONArray coinInfo = null;

			boolean isContinue = true;

			while (isContinue) {

				while (coinInfo == null) {
					question = "\n\n Coin Id :";
					result = ops.getInput(question).toString();

					coinInfo = c.getCoinInfo(result);
					
					if (coinInfo == null) {
						isContinue = true;
					}
				}

				String coinPrice = coinInfo.getJSONObject(0).get("price_usd").toString();
				System.out.println(
						" >> Receiving " + coinInfo.getJSONObject(0).getString("name") + "  --> " + coinPrice + " USD");

				question = "Asking Price :";
				result = ops.getInput(question).toString();

				double canBuyShares = availableUSDs / Double.parseDouble(result);
				System.out.println(
						" >> Receiving " + coinInfo.getJSONObject(0).getString("name") + " Amount : " + canBuyShares);

				Investor invest = new GamblingInvestor(usd2lkr, availableUSDs, coinInfo);
				invest.profitAnalyser(canBuyShares);
				
				coinInfo = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
