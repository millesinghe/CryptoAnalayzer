package com.cmcpredict.wallet;

import org.json.JSONArray;

public class GamblingInvestor extends Investor {

	private double change1H;

	private double change24H;

	private double change7D;

	public GamblingInvestor(String usd2lkr, double availableUSDs, JSONArray coinInfo) {
		super(usd2lkr, availableUSDs);
		this.setChange1H(coinInfo.getJSONObject(0).getString("percent_change_1h"));
		this.setChange24H(coinInfo.getJSONObject(0).getString("percent_change_24h"));
		this.setChange7D(coinInfo.getJSONObject(0).getString("percent_change_7d"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void profitAnalyser(double canBuyShares) {
		System.out.println("\n\n\n  =================== STATISTICS =============");
		System.out.println("\n >> Invested USD \t\t :" + availableUSDs + " USD");
		System.out.println(" >> LKR to USD Price \t\t :" + usd2lkr + " LKR");
		System.out.println(" >> Amount of Coins Recieved \t: " + canBuyShares);

		System.out.println("\n ================== PROFIT MARGINS =============");
		System.out.println("\n >> Sell within the ");

		double profitUSD = availableUSDs * (change1H / 100);
		double profitLKR = usd2lkr * profitUSD;
		System.out.println(" >>>>  Hour : Profit " + profitUSD + " USD (" + change1H + " %) " + profitLKR + " LKR");
		System.out.println("             Sell at: " + (availableUSDs + profitUSD) / canBuyShares + " USD\n");

		profitUSD = availableUSDs * (change24H / 100);
		profitLKR = usd2lkr * profitUSD;
		System.out.println(" >>>>  Day  : Profit " + profitUSD + " USD (" + change24H + " %) " + profitLKR + " LKR");
		System.out.println("             Sell at: " + (availableUSDs + profitUSD) / canBuyShares + " USD\n");

		profitUSD = availableUSDs * (change7D / 100);
		profitLKR = usd2lkr * profitUSD;
		System.out.println(" >>>>  Week : Profit " + profitUSD + " USD (" + change7D + " %) " + profitLKR + " LKR");
		System.out.println("             Sell at: " + (availableUSDs + profitUSD) / canBuyShares + " USD\n");
		System.out.println("=============== ================ =============");

	}

	public double getChange1H() {
		return change1H;
	}

	public void setChange1H(String change1h) {
		change1H = Double.parseDouble(change1h);
	}

	public double getChange24H() {
		return change24H;
	}

	public void setChange24H(String change24h) {
		change24H = Double.parseDouble(change24h);
	}

	public double getChange7D() {
		return change7D;
	}

	public void setChange7D(String change7d) {
		change7D = Double.parseDouble(change7d);
	}
}
