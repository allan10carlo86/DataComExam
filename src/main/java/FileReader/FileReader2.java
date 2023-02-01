package FileReader;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileReader2 {

	public Sheet readExcel(String filePath, String fileName, String sheetName) throws IOException {

		//Create an object of File class to open xlsx file
		String file = filePath + fileName;
		//Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook Workbook = null;
		//Find the file extension by splitting file name in substring  and getting only extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		//Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			//If it is xlsx file then create object of XSSFWorkbook class
			Workbook = new XSSFWorkbook(inputStream);
		}
		//Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			Workbook = new HSSFWorkbook(inputStream);
		}
		//Read sheet inside the workbook by its name

		Sheet Sheet = Workbook.getSheet(sheetName);
		
		return Sheet;
	}
	
	public int getColumnNum(Sheet Sheet, String header) throws IOException {
		//Find number of rows in excel file
		int columnNumber = -1;
		//Create a loop over all the rows of excel file to read it
		for (int i = 0; i < 1 + 1; i++) {
			Row row = Sheet.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < row.getLastCellNum(); j++) {
				// Print Excel data in console
				//System.out.print(row.getCell(j).getStringCellValue() + "|| ");
				if(row.getCell(j).getStringCellValue().equals(header))
				{
					columnNumber = j;
					break;
				}
			}
		}
		System.out.println("*****Column NUMBER IS " + columnNumber);
		return columnNumber;
	}

	public int getRowNum(Sheet Sheet, String testCase) throws IOException {
		int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
		int rowNumber = -1;
		//Create a loop over all the rows of excel file to read it
		for (int i = 0; i < rowCount + 1; i++) {
			Row row = Sheet.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < 1; j++) {
				// Print Excel data in console
				//System.out.print(row.getCell(j).getStringCellValue() + "|| ");
				if(row.getCell(j).getStringCellValue().equals(testCase))
				{
					rowNumber = i;
					break;
				}
			}
			if (rowNumber > -1){
				break;
			}
			System.out.println();
		}
		System.out.println("*****Row NUMBER IS " + rowNumber);
		
		return rowNumber;
	}
	
	public String returnValue(Sheet Sheet, int columnNumber, int rowNumber) throws IOException {		
		System.out.println("***VALUE IS " + Sheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue());
		return Sheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue();				
	}
	
	//Main function is calling readExcel function to read data from excel file
	public static void main(String... strings) throws IOException {

		//Create an object of ReadExcelFile class

		FileReader2 objExcelFile = new FileReader2();

		//Prepare the path of excel file

		String filePath = "datafile/";

		//Call read file method of the class to read data

		Sheet sheet = objExcelFile.readExcel(filePath, "Population.xlsx", "Sheet1");
		int columnNumber = objExcelFile.getColumnNum(sheet,"Capital");
		int rowNumber = objExcelFile.getRowNum(sheet,"Indonesia");
		System.out.println(objExcelFile.returnValue(sheet, columnNumber, rowNumber));
		
		int columnNumber1 = objExcelFile.getColumnNum(sheet,"Population");
		int rowNumber1 = objExcelFile.getRowNum(sheet,"Indonesia");
		System.out.println(objExcelFile.returnValue(sheet, columnNumber1, rowNumber1));
		
		int columnNumber2 = objExcelFile.getColumnNum(sheet,"Capital");
		int rowNumber2 = objExcelFile.getRowNum(sheet,"France");
		System.out.println(objExcelFile.returnValue(sheet, columnNumber2, rowNumber2));
	
	}
	
	

}
