package com.cmcpredict.automate;

import org.json.JSONArray;

import com.cmcpredict.helper.ExcelHandler;
import com.cmcpredict.operation.CurrencyData;

public class AutoDataCollector {

	public void automatedDataCollector() {
		System.out.println("Exporting coins info");
		
		CurrencyData data = new CurrencyData();
		JSONArray jsonArr = data.getCoinInfo("");
		ExcelHandler excel = new ExcelHandler();
		excel.writeToExcel(jsonArr);
		
		System.out.println("Finished Exporting coins info");
		
	}
}
