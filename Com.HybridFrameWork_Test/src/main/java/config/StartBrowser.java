package config;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StartBrowser {
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReports;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;

	@BeforeTest
	public static void report() {
		htmlReporter = new ExtentHtmlReporter("Reports/MyReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);
	}

	@BeforeMethod
	public static void beforeMethod(Method method) {
		parentTest = extentReports.createTest(method.getName());
	}

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
//	  /WebDriverManager.firefoxdriver().setup();

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		extentReports.flush();
	}

}
