package DataObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageAbstractClass {
	WebDriver _class_WebDriver;
	Wait _class_Wait;
	Actions _class_Actions;

	
	public PageAbstractClass(WebDriver driver, WebDriverWait wait, Actions actions
			) {
		_class_WebDriver = driver;
		_class_Wait = wait;
		_class_Actions = actions;
	}
}
