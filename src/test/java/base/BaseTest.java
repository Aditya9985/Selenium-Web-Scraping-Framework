package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

import utilities.ReadConfig;
import utilities.ExtentSetup;
import com.aventstack.extentreports.ExtentReports;

public class BaseTest {

    // Variables we will share across all our scripts
    public WebDriver browser;
    public static ExtentReports report;

    // This runs ONE TIME before the script starts
    public static void startReport() {
        // Create the report file
        report = ExtentSetup.setupReport();
    }

    // This runs BEFORE the script starts
    public void openBrowser() throws Exception {
        // 1. Read the browser name from config file
        String browserName = ReadConfig.getValue("browser");

        // 2. Open the correct browser depending on the config
        if (browserName.equalsIgnoreCase("firefox")) {
            browser = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            browser = new EdgeDriver();
        } else {
            // Default is Chrome if not specified
            browser = new ChromeDriver();
        }

        // 3. Make the browser wait maximum 10 seconds before throwing an error
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 4. Make browser full screen
        browser.manage().window().maximize();

        // 5. Open the website url from the config
        String websiteUrl = ReadConfig.getValue("url");
        browser.get(websiteUrl);
    }

    // This runs AFTER the script finishes
    public void closeBrowser() {
        if (browser != null) {
            // Close the browser window
            browser.quit();
        }
    }

    // This runs ONE TIME after the script is fully finished
    public static void saveReport() {
        if (report != null) {
            // Save all the logs into the HTML file
            report.flush();
        }
    }
}
