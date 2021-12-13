package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.BaseClass;
import pageobjects.Homepage;

public class LoginTest extends BaseClass {
	
	private static final Logger log = LogManager.getLogger(LoginTest.class);

	Homepage home = new Homepage(driver);

	@DataProvider(name = "testData")
	public Object[][] testData(Method m) throws FileNotFoundException, IOException {
		Object[][] arrayObject = utils.ExcelDataConfig.getDataFromExcel(getProperty("excel"), "login", m.getName());
		return arrayObject;
	}

	@Test(dataProvider = "testData",groups= {"smoke"})
	public void login1(HashMap<String, String> testData) throws Exception {

		driver.get(getProperty("url"));
		
		log.info("navigated to website");
		
		takeScreenShot();

		driver.manage().window().maximize();

		driver.findElement(By.id("user-name")).sendKeys(testData.get("username"));

		driver.findElement(By.id("password")).sendKeys(testData.get("password"));

		Thread.sleep(2000);

		/*
		 * driver.findElement(By.id("login-button")).click();
		 * 
		 * //home.filter.click();
		 * 
		 * selectByValue(home.filter, "hilo");
		 * 
		 * verifyElementsSorting(home.prices);
		 * 
		 * 
		 * clickListElements(home.addToCart);
		 * 
		 * click(home.cart);
		 * 
		 * 
		 * clickListElements(home.removeFromCart);
		 * 
		 * 
		 * scrollTillPageEnd();
		 * 
		 * String parent = driver.getWindowHandle();
		 * 
		 * //driver.findElement(By.xpath("//a[.='Twitter']")).click();
		 * 
		 * 
		 * 
		 * clickAndSwitchToWindows(home.socialLinks,parent);
		 * 
		 * Thread.sleep(3000) ;
		 */
	}

	@Test(dataProvider = "testData",groups= {"regression"})
	public void login2(HashMap<String, String> data) throws Exception {

		driver.get(getProperty("url"));
		

		driver.manage().window().maximize();

		driver.findElement(By.id("user-name")).sendKeys(data.get("username"));

		driver.findElement(By.id("password")).sendKeys(data.get("password"));

		Thread.sleep(2000);
	}

}
