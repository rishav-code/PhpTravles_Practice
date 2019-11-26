package com.atmecs.practice1.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atmecs.practice1.constant.FilePath;

public class ExcelWrite {
	//public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	public boolean setCellData(String filePath,String sheetName, String colName, String uniqueId, String data) {
		try {
			int rowNum = 0;
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
//		rowNum = getRowNumber(uniqueId, sheet);
//		if (rowNum <= 0)
//		return false;

		int index = workbook.getSheetIndex(sheetName);
		int colNum = -1;
		if (index == -1)
		return false;

		sheet = workbook.getSheetAt(index);
		rowNum = getRowNumber(uniqueId, sheet);
		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
		// System.out.println(row.getCell(i).getStringCellValue().trim());
		if (row.getCell(i).getStringCellValue().trim().equals(colName))
		colNum = i;
		}
		if (colNum == -1)
		return false;

		sheet.autoSizeColumn(colNum);
		row = sheet.getRow(rowNum);
		if (row == null)
		row = sheet.createRow(rowNum);

		cell = row.getCell(colNum);
		if (cell == null)
		cell = row.createCell(colNum);

//		 cell style
//		 CellStyle cs = workbook.createCellStyle();
//		 cs.setWrapText(true);
//		 cell.setCellStyle(cs);
		cell.setCellValue(data);

		fileOut = new FileOutputStream(filePath);

		workbook.write(fileOut);

		fileOut.close();

		} catch (Exception e) {
		e.printStackTrace();
		return false;
		}
		return true;
		}
	
	public int getRowNumber(String uniqueId, XSSFSheet workSheet) throws IOException {

		Iterator<Row> rowIterator = getDataOfRows(workSheet);
		rowIterator.next();
		int rowNumber = 0;
		while (rowIterator.hasNext()) {
			rowNumber++;
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				if (cell.toString().equalsIgnoreCase(uniqueId)) {
					return rowNumber;
				}

			}
		}
		return 0;
	}
	
	public Iterator<Row> getDataOfRows(XSSFSheet workSheet) {
		// iterating through rows and getting row number
		Iterator<Row> rows = workSheet.iterator();
		return rows;
	}
	
	public static void main(String[] args) {
		ExcelWrite excel=new ExcelWrite();
		excel.setCellData(FilePath.TESTDATA_FILE2,"dateinput", "Date", "later_Days", "saurabh");
	}


}
