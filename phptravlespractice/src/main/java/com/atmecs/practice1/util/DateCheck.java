package com.atmecs.practice1.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.atmecs.practice1.constant.FilePath;
import com.atmecs.practice1.constant.ValidateData;

public class DateCheck {
	LocalDate now;
	ValidateData data;
	ExcelWrite write = new ExcelWrite();
	ExcelReader excelFile = new ExcelReader();
	Scanner scan = new Scanner(System.in);

	public DateCheck() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		now = LocalDate.now();
		System.out.println("Todays date" + "" + dtf.format(now));

	}

	public void oneMonthLater() throws NullCellValueException {

		String noOfMonth = excelFile.getCellData(FilePath.TESTDATA_FILE2, "dateinput", "input", "Later_Month");
		int months = Integer.parseInt(noOfMonth);
		String inputYear = excelFile.getCellData(FilePath.TESTDATA_FILE2, "selectdate", "Year", "test1");
		int year = Integer.parseInt(inputYear);
		String inputMonth = excelFile.getCellData(FilePath.TESTDATA_FILE2, "selectdate", "Month", "test1");
		int month = Integer.parseInt(inputMonth);
		String inputDay = excelFile.getCellData(FilePath.TESTDATA_FILE2, "selectdate", "Day", "test1");
		int day = Integer.parseInt(inputDay);
		LocalDate currentdate = LocalDate.of(year, month, day);
		System.out.println("User input date " + " " + currentdate);
		LocalDate oneMonthBeforeDate = currentdate.plusMonths(months);
		System.out.println(" One month later date" + " " + String.valueOf(oneMonthBeforeDate));
		write.setCellData(FilePath.TESTDATA_FILE2, "dateinput", "Date", "Later_Month",
				String.valueOf(oneMonthBeforeDate));
	}

	public void currentLaterDate() throws NullCellValueException {
		String noOfMonth = excelFile.getCellData(FilePath.TESTDATA_FILE2, "dateinput", "input", "Later_Month");
		int months = Integer.parseInt(noOfMonth);
		now.plusDays(months);
		System.out.println(" One month later date" + " " + String.valueOf(now.plusMonths(months)));
		write.setCellData(FilePath.TESTDATA_FILE2, "dateinput", "Date", "Later_Month",
				String.valueOf(now.plusMonths(months)));

	}

	public void oneMonthEarlier() throws NullCellValueException {
		String noOfMonth = excelFile.getCellData(FilePath.TESTDATA_FILE2, "dateinput", "input", "Earlier_Month");
		int months = Integer.parseInt(noOfMonth);
		String inputYear = excelFile.getCellData(FilePath.TESTDATA_FILE2, "selectdate", "Year", "test1");
		int year = Integer.parseInt(inputYear);
		String inputMonth = excelFile.getCellData(FilePath.TESTDATA_FILE2, "selectdate", "Month", "test1");
		int month = Integer.parseInt(inputMonth);
		String inputDay = excelFile.getCellData(FilePath.TESTDATA_FILE2, "selectdate", "Day", "test1");
		int day = Integer.parseInt(inputDay);
		LocalDate currentdate = LocalDate.of(year, month, day);
		System.out.println("User input date " + " " + currentdate);
		LocalDate oneMonthAfterDate = currentdate.minusMonths(months);
		System.out.println(" One month before date" + " " + String.valueOf(oneMonthAfterDate));
		write.setCellData(FilePath.TESTDATA_FILE2, "dateinput", "Date", "Earlier_Month",
				String.valueOf(now.plusMonths(months)));

	}

	public void currentDateEarlier() throws NullCellValueException {
		String noOfMonth = excelFile.getCellData(FilePath.TESTDATA_FILE2, "dateinput", "input", "Earlier_Month");
		int months = Integer.parseInt(noOfMonth);
		LocalDate currentdate = LocalDate.now();
		now.minusMonths(months);
		System.out.println(" One month before date" + " " + String.valueOf(now.minusMonths(months)));
		write.setCellData(FilePath.TESTDATA_FILE2, "dateinput", "Date", "Earlier_Month",
				String.valueOf(now.minusMonths(months)));

	}

	public void tenDaysLater() throws NullCellValueException {
		String noOfDays = excelFile.getCellData(FilePath.TESTDATA_FILE2, "dateinput", "input", "later_Days");
		int days = Integer.parseInt(noOfDays);
		String inputYear = excelFile.getCellData(FilePath.TESTDATA_FILE2, "selectdate", "Year", "test1");
		int year = Integer.parseInt(inputYear);
		String inputMonth = excelFile.getCellData(FilePath.TESTDATA_FILE2, "selectdate", "Month", "test1");
		int month = Integer.parseInt(inputMonth);
		String inputDay = excelFile.getCellData(FilePath.TESTDATA_FILE2, "selectdate", "Day", "test1");
		int day = Integer.parseInt(inputDay);
		LocalDate currentdate = LocalDate.of(year, month, day);
		System.out.println("User input date " + " " + currentdate);
		LocalDate tenDaysLater = currentdate.plusDays(days);
		System.out.println(" Ten days later date" + " " + String.valueOf(tenDaysLater));
		write.setCellData(FilePath.TESTDATA_FILE2, "dateinput", "Date", "later_Days",
				String.valueOf(now.plusMonths(days)));

	}

	public void currentDateForDays() throws NullCellValueException {
		String noOfDays = excelFile.getCellData(FilePath.TESTDATA_FILE2, "dateinput", "input", "later_Days");
		int days = Integer.parseInt(noOfDays);
		now.plusDays(days);
		System.out.println(" Ten days later date" + " " + String.valueOf(now.plusDays(days)));
		write.setCellData(FilePath.TESTDATA_FILE2, "dateinput", "Date", "later_Days",
				String.valueOf(now.plusDays(days)));

	}

	public static void main(String[] args) {
		DateCheck check = new DateCheck();
		try {
			check.currentDateEarlier();
			check.currentLaterDate();
			check.currentDateForDays();
		} catch (Exception | NullCellValueException e) {
			e.printStackTrace();

		}

	}
}
