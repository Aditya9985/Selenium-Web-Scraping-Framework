package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class TakeScreenshot {

    public static String capture(WebDriver driver, String name) throws Exception {
        String path = ""; //Creates an empty "box" to hold the final address of the photo later.


        TakesScreenshot ts = (TakesScreenshot) driver;
        File memoryFile = ts.getScreenshotAs(OutputType.FILE);
        String time = new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());
        path = System.getProperty("user.dir") + "/screenshots/" + name + "_" + time + ".png";
        File hardDriveFile = new File(path);
        FileUtils.copyFile(memoryFile, hardDriveFile);

        return path;
    }
}
