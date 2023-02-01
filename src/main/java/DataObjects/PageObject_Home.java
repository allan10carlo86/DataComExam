package DataObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject_Home extends PageAbstractClass{
	
	

	public PageObject_Home(WebDriver webDriver, WebDriverWait webDriverWait, Actions actions) {
		// TODO Auto-generated constructor stub
		super(webDriver, webDriverWait, actions);
	}

	By menu_hamburger = By.xpath("//button//span[contains(text(),'Menu')]");
	By menu_payees = By.xpath("//a//span[contains(text(), 'Payees')]");
	By menu_payOrTransfer = By.xpath("//button//span[text()='Pay or transfer']");

	
	public void navigateToPayees() {
		_class_Wait.until(ExpectedConditions.elementToBeClickable(menu_hamburger));
		_class_WebDriver.findElement(menu_hamburger).click();
		_class_Wait.until(ExpectedConditions.elementToBeClickable(menu_payees));
		_class_WebDriver.findElement(menu_payees).click();
	}
	
	public void navigateToPayments() {
		_class_Wait.until(ExpectedConditions.elementToBeClickable(menu_hamburger));
		_class_WebDriver.findElement(menu_hamburger).click();
		_class_Wait.until(ExpectedConditions.elementToBeClickable(menu_payOrTransfer));
		_class_WebDriver.findElement(menu_payOrTransfer).click();
	}
}
