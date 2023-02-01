package DataObjects;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.TestResult;
import Utilities.Utilities;

public class WebDriverClass {

		private final long WAIT_FOR_ELEMENT_TIMEOUT = 15000;
		public ThreadLocal<WebDriver> driver;
		public ThreadLocal<WebDriverWait> wait;
		public ThreadLocal<Actions> actions;
		
		public PageObject_Home homePage;
		public PageObject_Payees payeesPage;
		public PageObject_Payments paymentsPage;
		
		public static ExtentReports reports;
		public ExtentTest test;
		
		public static ExtentSparkReporter sparkReport;
		@BeforeClass
		public static void setup() throws ClassNotFoundException {
			// initialize the HtmlReporter
			reports = new ExtentReports();
			sparkReport = new ExtentSparkReporter("reports/report.html");
			reports.attachReporter(sparkReport);
		}
		
		@Before
		public void before() throws IOException {
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
			reports.createTest("****Method Name: " + methodName);
			setBrowser(Utilities.getValueFromExcel(
					"00_Browsers", 
					"Browser", 
					"Chrome1"));
			openWindow(Utilities.getValueFromExcel(
					"01_URL", 
					"URL", 
					"login"));
			homePage = new PageObject_Home(driver.get(), wait.get(), actions.get());
			payeesPage = new PageObject_Payees(driver.get(), wait.get(), actions.get());
			paymentsPage = new PageObject_Payments(driver.get(), wait.get(), actions.get());	
		}
		
		@After
		public void close() 
		{
			driver.get().close();
		}
		@AfterClass 
		public static void afterClassTearDown() {
			
			
			reports.flush();
		}
		
		public void setBrowser(String browser) {
			driver = new ThreadLocal<>();
			wait = new ThreadLocal<>();
			actions = new ThreadLocal<>();
			
			switch(browser) {
				case "Chrome":
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--whitelisted-ips=\"\"");
					driver.set(new ChromeDriver(options));
					break;
				case "Firefox":
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions optionsFirefox = new FirefoxOptions();
					optionsFirefox.addArguments("--whitelisted-ips=\"\"");
					driver.set(new FirefoxDriver(optionsFirefox));
					break;
				default:
					throw new IllegalArgumentException(browser);
			
			}
			
			wait.set(new WebDriverWait(driver.get(), 10000));
			actions.set(new Actions(driver.get()));

		}
		
		
		public void openWindow(String url) {
			driver.get().navigate().to(url);
		}
		


}
