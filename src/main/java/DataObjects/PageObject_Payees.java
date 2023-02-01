package DataObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject_Payees extends PageAbstractClass{

	public PageObject_Payees(WebDriver driver, WebDriverWait wait, Actions actions) {
		super(driver, wait, actions);
		// TODO Auto-generated constructor stub
	}
	private static final String str_payees = "Payees";
	private static final String str_error_message = "A problem was found. Please correct the field highlighted below.";
	private static final boolean bool_isAscendingExpected = true;
	private static final boolean bool_isDescendingExpected = true;
	By h1_payees = By.xpath("//h1//span[contains(text(),'Payees')]");
	By addButton = By.xpath("//button//span[contains(text(),'Add')]");
	By payeeName = By.xpath("//label[text()='Payee Name']//following-sibling::div//input");
	By bankTextBox = By.xpath("//label[text()='Account']//following-sibling::input[1]");
	By bankBranchTextBox = By.xpath("//label[text()='Account']//following-sibling::input[2]");
	By bankAccountTextBox = By.xpath("//label[text()='Account']//following-sibling::input[3]");
	By suffixTextBox = By.xpath("//label[text()='Account']//following-sibling::input[4]");
	By addButtonPayee = By.xpath("//button[text()='Add']");
	By validationMessagePayeeAdded = By.xpath("//span[text()='Payee added']");
	By problemFoundError = By.xpath("//div[contains(text(), 'A problem was found.')]");
	By payeeListElements = By.xpath("//p/span[@class='js-payee-name']");
	By nameSorterButton = By.xpath("//h3/span[text()='Name']");
	
	public void validatePayeesPage() {
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(h1_payees));
		String textFromValidation = _class_WebDriver.findElement(h1_payees).getText();
		Assert.assertEquals(str_payees, textFromValidation);
	}
	
	public void enterPayeeDetails(  String inputPayeeName, 
									String inputBankName,
									String inputBranchName,
									String inputAccount,
									String inputSuffix) {
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addButton));
		_class_WebDriver.findElement(addButton).click();
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(payeeName));
		_class_WebDriver.findElement(payeeName).sendKeys(inputPayeeName);
		_class_Actions.click(_class_WebDriver.findElement(payeeName)).sendKeys(Keys.ENTER).build().perform();
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bankTextBox));
		_class_WebDriver.findElement(bankTextBox).sendKeys(inputBankName);
		_class_WebDriver.findElement(bankBranchTextBox).sendKeys(inputBranchName);
		_class_WebDriver.findElement(bankAccountTextBox).sendKeys(inputAccount);
		_class_WebDriver.findElement(suffixTextBox).sendKeys(inputSuffix);
		_class_WebDriver.findElement(addButtonPayee).click();
		_class_Wait.until(ExpectedConditions.presenceOfElementLocated(validationMessagePayeeAdded));
	}
	
	public void validatePayeeTextBox(
			String inputPayeeName)
	 {
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addButton));
		_class_WebDriver.findElement(addButton).click();
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(payeeName));
		_class_WebDriver.findElement(addButtonPayee).click();
		_class_Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(problemFoundError));
		String STR_ERROR_MESSAGE_FROM_WEBSITE = _class_WebDriver.findElement(problemFoundError).getText();
		Assert.assertEquals(str_error_message, STR_ERROR_MESSAGE_FROM_WEBSITE);
		_class_WebDriver.findElement(payeeName).sendKeys(inputPayeeName);
		_class_Actions.click(_class_WebDriver.findElement(payeeName)).sendKeys(Keys.ENTER).build().perform();
		
		_class_Wait.until(ExpectedConditions.invisibilityOfElementLocated(problemFoundError));
		boolean ifErrorIsInvisible = false;
		try {
			STR_ERROR_MESSAGE_FROM_WEBSITE = _class_WebDriver.findElement(problemFoundError).getText();
		} catch(Exception e) {
			ifErrorIsInvisible = true;
			System.out.println("*******ERROR MESSAGE IS NOT SEEN*****");
		}
		Assert.assertEquals(true, ifErrorIsInvisible);
	}
	
	public void validateAscending() {
		List<WebElement> payeeList = _class_WebDriver.findElements(payeeListElements);
		String strArry [] = new String[payeeList.size()];
		
		while(payeeList.isEmpty())
		for(int i=0;i < payeeList.size();i++)
		{
			strArry[i]=payeeList.get(i).getText();
		}
		/*
		 * Compare the String
		 */
		boolean isAscending = true;
		outer: 
		for(int i=0;i < strArry.length;i++)
		{
			for(int j=i+1;j < i;j++)
			{
				int result = strArry[j].compareTo(strArry[i]);
				if(!(result > 0))
				{
						System.out.println("Data in the Table is not SORTED::" +strArry[j]+":::"+ strArry[i]);
						isAscending=false;
						break outer;
				}
				else
				{
					 System.out.println("Data in the Table is SORTED::" +strArry[j]+":::"+ strArry[i]);
				}
			}
		}
		
		if(isAscending) {
			System.out.println("********** Data is SORTED Ascendingly");
		} else {
			System.out.println("********** Data is NOT SORTED Ascendingly");

		}
		Assert.assertEquals(bool_isAscendingExpected, isAscending);
	}
	public void clickNameSorter() {
		_class_WebDriver.findElement(nameSorterButton).click();
	}
	public void validateDescending() {
		List<WebElement> payeeList = _class_WebDriver.findElements(payeeListElements);
		String strArry [] = new String[payeeList.size()];
		
		while(payeeList.isEmpty())
		for(int i=0;i < payeeList.size();i++)
		{
			strArry[i]=payeeList.get(i).getText();
		}
		/*
		 * Compare the String
		 */
		boolean isDecending = true;
		outer: 
		for(int i=0;i < strArry.length;i++)
		{
			for(int j=i+1;j < i;j++)
			{
				int result = strArry[j].compareTo(strArry[i]);
				if(!(result < 0))
				{
						System.out.println("Data in the Table is not SORTED::" +strArry[j]+":::"+ strArry[i]);
						isDecending=false;
						break outer;
				}
				else
				{
					 System.out.println("Data in the Table is SORTED::" +strArry[j]+":::"+ strArry[i]);
				}
			}
		}
		
		if(isDecending) {
			System.out.println("********** Data is SORTED DESCENDINGLY");
		} else {
			System.out.println("********** Data is NOT SORTED DESCENDINGLY");

		}
		Assert.assertEquals(bool_isDescendingExpected, isDecending);
	}
	
}
