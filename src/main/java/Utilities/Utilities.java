package Utilities;


import java.io.IOException;
import java.text.ParseException;

import org.apache.poi.ss.usermodel.Sheet;

import FileReader.FileReader2;

public class Utilities {
	
	public String getAmount(String str_amount) {
	    String[] result = str_amount.split(" ");
	    //System.out.println(result[0]);
	    return result[0]; 
	
	}
	
	public double formatNumber(String str_amount) {
		return Double.parseDouble(str_amount.replaceAll("[$,]", ""));
	}
	
	public static String getValueFromExcel(String sheetOfValue, String column, String testCase) throws IOException {
		FileReader2 fileReader = new FileReader2();
		Sheet sheet = fileReader.readExcel("datafile/", "datafile.xlsx", sheetOfValue);
		int intColumn = fileReader.getColumnNum(sheet, column);
		int intRow = fileReader.getRowNum(sheet, testCase);
		return fileReader.returnValue(sheet, intColumn, intRow);
	}
	
	public static void main(String args[]) throws ParseException {
		String amount = "$5,000.00";
		Utilities util = new Utilities();
		Double number = util.formatNumber(amount);
		System.out.println(number+500);

	}
}
