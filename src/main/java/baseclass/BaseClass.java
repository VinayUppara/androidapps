package baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import utils.CaptureScreenshots;

public class BaseClass {

	public static WebDriver driver;
	public static WebDriverWait wait;

	static {

		System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();

	}

	@AfterTest(alwaysRun=true)
	public void closeDriver() {

		driver.quit();
	}

	public void click(WebElement ele) {

		ele.click();
	}

	public String getProperty(String key) throws FileNotFoundException, IOException {


		Properties prop = new Properties();

		File propertiesFile = new File(System.getProperty("user.dir")+"/src/test/resources/application.properties");

		prop.load(new FileInputStream(propertiesFile));


		return prop.getProperty(key);
	}

	public void waitForElementAndClick(WebElement ele) {

		wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(ele));

		ele.click();
	}

	public void waitForElementAndSendkeys(WebElement ele,String value) {

		wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(ele));

		ele.sendKeys(value);
	}

	public void moveAndClick(WebElement ele1, WebElement ele2) {


		Actions action = new Actions(driver);

		action.moveToElement(ele1).click(ele2).build().perform();
	}

	public void clickListElements(List<WebElement> ele) throws InterruptedException {

		for(WebElement e: ele) {
			e.click();
			Thread.sleep(2000);
		}
	}

	public void selectByValue(WebElement e, String value) {
		Select s = new Select(e);

		s.selectByValue(value);
	}

	public void verifyElementsSorting(List<WebElement> ele) {

		for(WebElement e : ele) {

			System.out.println(e.getText());
		}


	}

	public void scrollTillPageEnd() {

		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void switchToChildWindow(String parent) {


		Set<String> windowhandles = driver.getWindowHandles(); // 2 handles

		for(String window : windowhandles) { // go through the set of window handles


			if(!window.equals(parent)) {

				driver.switchTo().window(window); // switch to child

				System.out.println(driver.getTitle()); //perform some action on child window

				driver.close(); // close parent

				// switch to child
			}

		}
		driver.switchTo().window(parent);

	}

	public void clickAndSwitchToWindows(List<WebElement> elements, String parent) {

		for(WebElement e: elements) {

			click(e);
			switchToChildWindow(parent);

		}

	}
	
	public void takeScreenShot() throws IOException {
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss"); 
		String date=simpleDateFormat.format(new Date()); 
		
		
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(f, new File("./screenshots/"+date+".png"));
		
	}

	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result)
    {

        if(ITestResult.FAILURE==result.getStatus()) {
            CaptureScreenshots.capturescreen(driver,result.getName(),"FAILURE");
        }
        else {
            CaptureScreenshots.capturescreen(driver,result.getName(),"SUCCESS");
        }
    }


}