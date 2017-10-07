package com.cmcpredict.automate;

import java.net.URISyntaxException;

import org.json.JSONArray;

import com.cmcpredict.helper.ExcelHandler;
import com.cmcpredict.operation.CurrencyData;

public class AutoDataCollector {

	public void automatedDataCollector() {
		System.out.println("Exporting coins info");
		
		CurrencyData data = new CurrencyData();
		JSONArray jsonArr = data.getCoinInfo("");
		ExcelHandler excel = new ExcelHandler();
		try {
			excel.writeToExcel(jsonArr);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finished Exporting coins info");
		
	}
}
