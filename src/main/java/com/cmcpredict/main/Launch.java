package com.cmcpredict.main;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;

import com.cmcpredict.helper.ExcelHandler;
import com.cmcpredict.helper.PropertyHandler;
import com.cmcpredict.helper.SystemHelper;
import com.cmcpredict.operation.Coin;
import com.cmcpredict.operation.CurrencyData;

public class Launch {

	public static void main(String[] args) throws IOException {
		
		SystemHelper ops = new SystemHelper();
		
		String[] option = {"s","A. Request Coins Data \nB. Calculate Stock Performances \nC. Exit\nSelect the Operation >>"};
		String result = ops.getInput(option).toString();
		
		if (result.equalsIgnoreCase("A")) {
			CryptoCalc cry = new CryptoCalc();
			cry.requestCoinsData();
		} else if (result.equalsIgnoreCase("B")) {
			
			
		} else if (result.equalsIgnoreCase("C")) {
			System.exit(0);
		}
	}

	
}
