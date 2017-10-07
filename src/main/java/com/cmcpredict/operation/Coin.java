package com.cmcpredict.operation;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cmcpredict.helper.PropertyHandler;
import com.cmcpredict.main.CryptoCalc;

public class Coin {

	private String coinAmount;

	private String coinID;

	private JSONArray coinInfo;

	private String usd2lkr;

	private boolean isInvested = false;

	public Coin(String coinID, String usd2lkr) {
		this.usd2lkr = usd2lkr;
		this.coinID = coinID;
		
	}

	public void processWalletValuation() {
		PropertyHandler prop = new PropertyHandler();
		this.coinAmount = prop.readSystemPropery(coinID + ".total.amount");
		System.out.println(coinAmount);
		CurrencyData c = new CurrencyData();
		coinInfo = c.getCoinInfo(coinID);
		if (Double.valueOf(coinAmount) > 0) {
			this.isInvested = true;
		}
	}
	
	public void calculateValue() {
		if (this.isInvested) {
			CryptoCalc calc = new CryptoCalc();
			calc.startAnalyse(this.coinInfo.getJSONObject(0).getString("name"), this.usd2lkr,
					this.coinInfo.getJSONObject(0).getString("price_usd"), this.coinAmount);
		}
	}

	public JSONArray getDataListJSON() {
		return coinInfo;
	}

	/**
	 * @return the coinID
	 */
	public String getCoinID() {
		return coinID;
	}

	public String getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(String coinAmount) {
		this.coinAmount = coinAmount;
	}

	public JSONArray getCoinInfo() {
		return coinInfo;
	}

	public void setCoinInfo(JSONArray coinInfo) {
		this.coinInfo = coinInfo;
	}

	public String getUsd2lkr() {
		return usd2lkr;
	}

	public void setUsd2lkr(String usd2lkr) {
		this.usd2lkr = usd2lkr;
	}

	public boolean isInvested() {
		return isInvested;
	}

	public void setInvested(boolean isInvested) {
		this.isInvested = isInvested;
	}

	public void setCoinID(String coinID) {
		this.coinID = coinID;
	}
	
	
	
}
