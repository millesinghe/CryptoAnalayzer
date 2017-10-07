package com.cmcpredict.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Timestamp;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cmcpredict.util.Constant;

public class ExcelHandler {

	public void writeToExcel(JSONArray data) throws URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(Constant.COIN_HISTORY_EXCEL_PATH).getFile());

		XSSFWorkbook workbook = null;
		// Check file existence
		if (file.exists() == false) {
			// Create new file if it does not exist
			workbook = new XSSFWorkbook();
		} else {
			try {
				InputStream is = new FileInputStream(file);
				workbook = new XSSFWorkbook(is);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String time = timestamp.toString();
		PropertyHandler pp = new PropertyHandler();
		pp.writeToSystemPorperty("system/system", "CMC.last.data.updation.excel", time);

		for (int i = 0; i < data.length(); ++i) {
			String[] list = new String[6];
			JSONObject rec = data.getJSONObject(i);

			String name = rec.get("name").toString().replace("/", "").replace("[", "").replace("]", "");

			XSSFSheet sheet = workbook.getSheet(name);
			if (sheet == null) {
				sheet = workbook.createSheet(name);
				Row row = sheet.createRow(0);
				list[0]="Date";
				list[1]="LastUpdate";
				list[2]="Per1H";
				list[3]="Per24H";
				list[4]="Per7D";
				list[5]="NowPrice";
				for (int j = 0; j < list.length; j++) {
					Cell cell1 = row.createCell(j);
					cell1.setCellValue(list[j]);
				}
			}

			list[0] = time;

			String price = rec.get("price_usd").toString();
			list[5] = price;

			String lastUpdate = rec.get("last_updated").toString();
			list[1] = lastUpdate;

			String chng1 = rec.get("percent_change_1h").toString();
			list[2] = chng1;

			String chng24 = rec.get("percent_change_24h").toString();
			list[3] = chng24;

			String chng7 = rec.get("percent_change_7d").toString();
			list[4] = chng7;

			int rowCount = sheet.getLastRowNum() + 1;
			Row row = sheet.createRow(rowCount);

			for (int j = 0; j < list.length; j++) {
				Cell cell1 = row.createCell(j);
				cell1.setCellValue(list[j]);
			}
		}

		try {
			// OutputStream output = new FileOutputStream(file);
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
