package wdcmds;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.StartBrowser;

public class ActionDriver {
	public static WebDriver driver;
	public ActionDriver() {
		driver= StartBrowser.driver;
	}
	/**
	 * 
	 * @param url
	 * @throws Exception
	 */
	public static void navigateToApplication(String url)throws Exception {
		try {
			driver.get(url);
			StartBrowser.childTest.pass("Navigate :"+url);
		} catch (Exception e) {
			StartBrowser.childTest.fail("Unable To Navigate :"+url);
			throw e;

		}
	}
/**
 * used to perform click on buttons,links,RB and Check Boxes
 * @param locator -- get it from Object Repository
 * @param elementName -- Name of the element
 * @throws Exception
 */
	public  void click(By ele,String elementName) throws Exception {
		try {
			driver.findElement(ele).click();
			StartBrowser.childTest.pass("Performing :"+elementName);
		} catch (Exception e) {
			StartBrowser.childTest.fail("Unable To Performing :"+elementName);
			throw e;
		}
	}
	/**
	 * 
	 * @param locator
	 * @param elementName
	 * @param testData
	 * @throws Exception
	 */
	public  void type(By locator,String elementName,String testData) throws Exception {
		try {
			driver.findElement(locator).sendKeys(testData);
			StartBrowser.childTest.pass("Performing Type :"+elementName+"With Data"+testData);
		} catch (Exception e) {
			StartBrowser.childTest.fail("Unable To Performing :"+elementName+"With Data"+testData);
			throw e;
		}
	}
	/**
	 * 
	 * @param locator
	 * @return
	 */
		public String getText(By locator) {
			try {
				String x=driver.findElement(locator).getText();
				StartBrowser.childTest.pass("Test is :"+x);
				return x;
			} catch (Exception e) {
				StartBrowser.childTest.pass("Unable to return Text");
				return null;
			}
		}
		/**
		 * 
		 * @param locator
		 * @param elementName
		 */
			public void mouseHover(By locator,String elementName) {
				try {
					Actions a=new Actions(driver);
					WebElement mo=driver.findElement(locator);
					a.moveToElement(mo).build().perform();
					StartBrowser.childTest.pass("Perform MouseHover On:"+elementName);

				} catch (Exception e) {
					StartBrowser.childTest.fail("Unable to Perform MouseHover On:"+elementName);
				}
				
			}
			/**
			 * 
			 * @param locator
			 * @param expText
			 */
			public void verifyText(By locator,String expText) {
				String actualText=getText(locator);
				if(actualText.equals(expText)) {
					StartBrowser.childTest.pass("Actual Text is Equal to Expected Text");

				}else {
					StartBrowser.childTest.pass("Actual Text"+actualText+" is Not Equal to Expected Text"+expText);
					
				}
				
			}
			/**
			 * 
			 * @param locator
			 */
			public void waitForElementVisible(By locator) {
				WebDriverWait wait=new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
			/**
			 * 
			 * @param locator
			 * @param index
			 */
		public void selectedByIndex(By locator,int index) {
			try {
				WebElement dd= driver.findElement(locator);
				Select s=new Select(dd);
				s.selectByIndex(index);
				StartBrowser.childTest.pass("Selected"+index+"Value fromdropdown");

			} catch (Exception e) {
				StartBrowser.childTest.pass("Unable to Selected"+index+"Value fromdropdown");
			}
	
}
		/**
		 * 
		 * @return
		 */
//		public String getParentWindow() {
//			return driver.getWindowHandle();
//		}
		/**
		 * 
		 * @param locator
		 * @param visibleText
		 */
		public void selectByVisibleText(By locator,String visibleText) {
			try {
				WebElement dd= driver.findElement(locator);
				Select s=new Select(dd);
				s.selectByVisibleText(visibleText);
				StartBrowser.childTest.pass("Selected"+visibleText+"Value fromdropdown");

			} catch (Exception e) {
				StartBrowser.childTest.pass("Unable to Selected"+visibleText+"Value fromdropdown");
			}
		}
		/**
		 * 
		 * @return
		 */
		public String getParentWindow() {
		return driver.getWindowHandle();
	}
		/**
		 * 
		 * @param locator
		 * @param expText
		 */
		public void switchToChildWindow(By locator,String expText) {
			Set<String>windows=driver.getWindowHandles();
			try {
				for (String string:windows) {
					driver.switchTo().window(string);
					{
						if (getText(locator).equals(expText)) {
							System.out.println("I am in Correct Window");
							break;
						}
						else {
							System.out.println("I am in Wrong Window");
						}
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		public String screenShot() {
			return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
			
		}
		/**
		 * 
		 * @param menuLocator
		 * @param subMenuLocator
		 * @param menu
		 * @param subMenu
		 */
		public void mouseHoverAndClickSubMenu(By menuLocator,By subMenuLocator,String menu,String subMenu) {
			try {
				Actions act=new Actions(driver);
				WebElement ele=driver.findElement(menuLocator);
				act.moveToElement(ele).build().perform();
				Thread.sleep(5000);
				driver.findElement(subMenuLocator).click();
			} catch (Exception e) {
				StartBrowser.childTest.pass("Successfully mouse hover on Menu"+menu+"and Clicked on Sub menu :"+subMenu);
			}
		}
		public boolean isElementPresent(By locator,String elementName) {
			try {
				driver.findElement(locator);
				StartBrowser.childTest.pass("Element :"+elementName+" is present");
				return true;
			} catch (Exception e) {
				System.out.println("Element is Not Present");
				return false;
			}
			
		}
}