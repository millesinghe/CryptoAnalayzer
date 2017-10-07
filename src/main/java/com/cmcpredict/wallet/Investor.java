package com.cmcpredict.wallet;

import com.cmcpredict.helper.PropertyHandler;

public abstract class Investor {
	
	protected double usd2lkr;

	protected double availableUSDs;
	
	public Investor(String usd2lkr, double availableUSDs) {
		this.usd2lkr= Double.parseDouble(usd2lkr);
		this.availableUSDs = availableUSDs;
	}

	public void name(String coinId) {
		String upperCoindID = coinId.toUpperCase();

		String strCostAmount = new PropertyHandler().readUserPropery(upperCoindID + ".amount");
		String strCostTxFee = new PropertyHandler().readUserPropery(upperCoindID + ".total.tx.fees.btc");

		// NowShare Cost = (SharePrice x ShareAmount) + Tx Cost
		// cummulative Spend = PrevTxCost + NowTxCost

		// Cummulative Shares = PrevShares + NowShares

//		this.coinCost = Double.parseDouble(strCostAmount) + Double.parseDouble(strCostTxFee);
	}

	abstract public void profitAnalyser(double canBuyShares);

}
