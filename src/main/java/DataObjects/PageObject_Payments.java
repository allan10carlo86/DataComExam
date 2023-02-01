package DataObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.Utilities;

public class PageObject_Payments extends PageAbstractClass{

	By fromAccountElement = By.xpath("//button//span[text()='Choose account']");
	By toAccountElement = By.xpath("//button//span[text()='Choose account, payee, or someone new']");
	By accountsElementSelection = By.xpath("//li/span[text()='Accounts']");
	By everyDayElement = By.xpath("//p[text()='Everyday']/parent::div/parent::div/parent::div/parent::button");
	By everydayAmountElement = By.xpath("//p[text()='Everyday']//following-sibling::p[1]");
	By billsElement = By.xpath("//p[text()='Bills ']/parent::div/parent::div/parent::div/parent::button");
	By billsAmountElement = By.xpath("//p[text()='Bills ']//following-sibling::p[1]");
	By amountTextFieldElement = By.xpath("//label/span[text()='Amount']/parent::label//following-sibling::div//input");
	By transferButtonElement = By.xpath("//button//span[text()='Transfer']");
	By validationMessageTransferSuccessful = By.xpath("//span[text()='Transfer successful']");
	By everdayNewElementAmount = By.xpath("//span//h3[text()='Everyday']//parent::span/following-sibling::span[2]");
	By billsNewElementAmount = By.xpath("//span//h3[text()='Bills ']//parent::span/following-sibling::span[2]");
	Utilities util;
	
	String str_TransferSuccessful_Expected = "Transfer successful";
	String str_billsAmount;
	String str_everydayAmount;
	
	double dbl_everydayAmount;
	double dbl_billsAmount;
	
	
	public PageObject_Payments(WebDriver driver, WebDriverWait wait, Actions actions) {
		super(driver, wait, actions);
		util = new Utilities();
		// TODO Auto-generated constructor stub
	}

	public void payOrTransfer(double dbl_amount) {
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fromAccountElement));
		_class_WebDriver.findElement(fromAccountElement).click();
		
		str_everydayAmount = _class_WebDriver.findElement(everydayAmountElement).getText();
		dbl_everydayAmount = this.util.formatNumber(this.util.getAmount(str_everydayAmount));
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(everyDayElement));
		_class_WebDriver.findElement(everyDayElement).sendKeys(Keys.ENTER);
		
		_class_WebDriver.findElement(toAccountElement).click();
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(accountsElementSelection));
		WebElement ele = _class_WebDriver.findElement(accountsElementSelection);
		JavascriptExecutor jse = (JavascriptExecutor)_class_WebDriver;
		jse.executeScript("arguments[0].click()", ele);
		str_billsAmount = _class_WebDriver.findElement(billsAmountElement).getText();
		dbl_billsAmount = this.util.formatNumber(this.util.getAmount(str_billsAmount));
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(billsElement));
		_class_WebDriver.findElement(billsElement).sendKeys(Keys.ENTER);

		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(amountTextFieldElement));
		_class_WebDriver.findElement(amountTextFieldElement).sendKeys(Double.toString(dbl_amount));
		_class_WebDriver.findElement(transferButtonElement).click();
	
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(validationMessageTransferSuccessful));
		String str_TransferSuccessful_Actual = _class_WebDriver.findElement(validationMessageTransferSuccessful).getText();
		
		//System.out.println("str_TransferSuccessful_Expected  " + str_TransferSuccessful_Expected);		
		//System.out.println("str_TransferSuccessful_Actual " + str_TransferSuccessful_Actual);

		Assert.assertEquals(str_TransferSuccessful_Expected, str_TransferSuccessful_Actual);
		System.out.println("******* Transfer Successful ********");

		double dbl_everdayAmount_New = 
				this.util.formatNumber(_class_WebDriver.findElement(everdayNewElementAmount).getText());
				
		double dbl_billsAmount_New = 
				this.util.formatNumber(_class_WebDriver.findElement(billsNewElementAmount).getText());
		//System.out.println("dbl_everydayAmount "+ dbl_everydayAmount);
		//System.out.println("dbl_billsAmount " + dbl_billsAmount);
		//System.out.println("dbl_everdayAmount_New " + dbl_everdayAmount_New);
		//System.out.println("dbl_billsAmount_New " + dbl_billsAmount_New);
		
		Assert.assertEquals(dbl_everydayAmount-dbl_amount, dbl_everdayAmount_New);
		Assert.assertEquals(dbl_billsAmount+dbl_amount, dbl_billsAmount_New);
		System.out.println("******* Validated Numbers Successful ********");
		
	}
	

}
