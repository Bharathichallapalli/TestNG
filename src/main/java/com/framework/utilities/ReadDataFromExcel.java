package com.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {
	
	public static String[][] readExcelData(String Workbook, String Sheet){
		
		String[][] data = null;
		
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+Workbook);// To read files in Java
			XSSFWorkbook wb = new XSSFWorkbook(fis); // to let java understand that it's excel file
			XSSFSheet sheet = wb.getSheet(Sheet);//to read sheet
			XSSFRow row = sheet.getRow(0);//to read specific row from sheet
			
			int totalrows = sheet.getPhysicalNumberOfRows();
			int totalcolumns = row.getPhysicalNumberOfCells();
			
			data = new String[totalrows-1][totalcolumns];
			
			for(int r =1; r<totalrows;r++) {
				for(int c=0;c<totalcolumns;c++) {
					row = sheet.getRow(r);
					Cell cell = row.getCell(c);
					if(cell!=null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
					}	
					data[r-1][c] = cell.getStringCellValue();
				}
			}
		
		
		} catch (IOException e) {		
			e.printStackTrace();
		} 		
		
		return data;
		
	}

}
