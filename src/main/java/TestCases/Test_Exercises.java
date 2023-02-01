package TestCases;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import DataObjects.PageObject_Home;
import DataObjects.PageObject_Payees;
import DataObjects.WebDriverClass;
import Utilities.Utilities;

public class Test_Exercises extends WebDriverClass{

	@Test
	public void testCase1_PayeesValidation() throws InterruptedException, IOException {
		homePage.navigateToPayees();
		payeesPage.validatePayeesPage();
		super.test.log(Status.PASS, "Hello this passed");
	}
	
	@Test
	public void testCase2_AddNewPayee() throws InterruptedException, IOException {
		homePage.navigateToPayees();
		payeesPage.validatePayeesPage();
		String STR_NAME = Utilities.getValueFromExcel("02_PAYEE", "NAME","TEST_CASE_2");
		String STR_ACCOUNT = Utilities.getValueFromExcel("02_PAYEE", "ACCOUNT", "TEST_CASE_2");
		String STR_BRANCH = Utilities.getValueFromExcel("02_PAYEE", "BRANCH", "TEST_CASE_2");
		String STR_ACCOUNT_NUMBER = Utilities.getValueFromExcel("02_PAYEE", "ACCOUNT_NUMBER", "TEST_CASE_2");
		String STR_SUFFIX = Utilities.getValueFromExcel("02_PAYEE", "SUFFIX", "TEST_CASE_2");		
		payeesPage.enterPayeeDetails(
				STR_NAME, STR_ACCOUNT, 
				STR_BRANCH, STR_ACCOUNT_NUMBER, STR_SUFFIX);
		super.test.log(Status.PASS, "Hello this passed");
	}
	
	@Test
	public void testCase3_ValidatePayeeName() throws InterruptedException, IOException {
		homePage.navigateToPayees();
		payeesPage.validatePayeesPage();
		String STR_NAME = Utilities.getValueFromExcel("02_PAYEE", "NAME","TEST_CASE_3");
		payeesPage.validatePayeeTextBox(STR_NAME);
		super.test.log(Status.PASS, "Hello this passed");
	}
	
	@Test
	public void testCase4_ValidateSorting() throws InterruptedException, IOException {
		homePage.navigateToPayees();
		payeesPage.validatePayeesPage();
		String STR_NAME = Utilities.getValueFromExcel("02_PAYEE", "NAME","TEST_CASE_4");
		String STR_ACCOUNT = Utilities.getValueFromExcel("02_PAYEE", "ACCOUNT", "TEST_CASE_4");
		String STR_BRANCH = Utilities.getValueFromExcel("02_PAYEE", "BRANCH", "TEST_CASE_4");
		String STR_ACCOUNT_NUMBER = Utilities.getValueFromExcel("02_PAYEE", "ACCOUNT_NUMBER", "TEST_CASE_4");
		String STR_SUFFIX = Utilities.getValueFromExcel("02_PAYEE", "SUFFIX", "TEST_CASE_4");		
		payeesPage.enterPayeeDetails( 
				STR_NAME, STR_ACCOUNT, 
				STR_BRANCH, STR_ACCOUNT_NUMBER, STR_SUFFIX);
		//Sorting 
		payeesPage.validateAscending();
		payeesPage.clickNameSorter();
		payeesPage.validateDescending();
		super.test.log(Status.PASS, "Hello this passed");
	}
	
	@Test
	public void testCase5_Payments() throws InterruptedException, IOException {
		this.homePage.navigateToPayments();
		double dbl_amount = Double.parseDouble(
				Utilities.getValueFromExcel("03_AMOUNT", "AMOUNT", "TEST_CASE_5"));
		this.paymentsPage.payOrTransfer(dbl_amount);
		super.test.log(Status.PASS, "Hello this passed");
	}
	
}

