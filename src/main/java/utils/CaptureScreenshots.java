package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshots {

	public static void capturescreen(WebDriver driver, String screenShotName, String status)
    {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            if (status.equals("FAILURE")) {
                FileUtils.copyFile(scrFile, new File("./ScreenshotsFailure/" + screenShotName + ".png"));
            }
            else if(status.equals("SUCCESS"))
            {
                FileUtils.copyFile(scrFile, new File("./ScreenshotsSuccess/" + screenShotName + ".png"));
            }


            System.out.println("Printing screen shot taken for className "+ screenShotName);

        }
        catch (Exception e)
        {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }

    }
}
	

