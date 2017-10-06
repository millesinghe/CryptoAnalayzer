package com.cmcpredict.wallet;

import com.cmcpredict.helper.PropertyHandler;
import com.cmcpredict.operation.Coin;

public class Investor {
	
	private double coinCost;
	
	private Coin coin;
	
	public Investor(String coinId) {
		
		String upperCoindID = coinId.toUpperCase();
		
		String strCostAmount =  new PropertyHandler().readUserPropery(upperCoindID +".amount");
		String strCostTxFee =  new PropertyHandler().readUserPropery(upperCoindID +".total.tx.fees.btc");

		//NowShare Cost = (SharePrice x ShareAmount) + Tx Cost
		//cummulative Spend = PrevTxCost + NowTxCost
		
		//Cummulative Shares = PrevShares + NowShares
		
		this.coinCost = Double.parseDouble(strCostAmount) + Double.parseDouble(strCostTxFee);
	}

	/**
	 * @return the coin
	 */
	public Coin getCoin() {
		return coin;
	}

	/**
	 * @param coin the coin to set
	 */
	public void setCoin(Coin coin) {
		this.coin = coin;
	}
	
	

}
