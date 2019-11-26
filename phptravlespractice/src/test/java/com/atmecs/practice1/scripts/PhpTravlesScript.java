package com.atmecs.practice1.scripts;

import org.testng.annotations.Test;

import com.atmecs.practice1.pages.PhpTravlesFuncationality;
import com.atmecs.practice1.reports.LogReport;
import com.atmecs.practice1.testbase.TestBase;
import com.atmecs.practice1.util.ExcelReader;
import com.atmecs.practice1.util.NullCellValueException;
public class PhpTravlesScript extends TestBase {
	LogReport log;
	ExcelReader excelFile;
	PhpTravlesFuncationality funcationality;
	@Test
	public void phpTravlesIntegration() throws NullCellValueException {
		funcationality=new PhpTravlesFuncationality(driver);
		funcationality.loginFunctionality();
		funcationality.accountPageValidation();
		funcationality.timeAndDateValidations();
		funcationality.randomNumberValidation();
		
	}

	

}
