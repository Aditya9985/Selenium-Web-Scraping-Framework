package tests;

import base.BaseTest;
import actions.RediffActions;
import utilities.WriteExcel;
import utilities.TakeScreenshot;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.util.List;

public class RediffScraperTest extends BaseTest {

    // Variables for this class
    ExtentTest testLog;
    RediffActions scraperActions;

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Rediff Web Scraper as a Java Application...");

        // 1. Create an instance of our own scraper script
        RediffScraperTest myScraper = new RediffScraperTest();

        // 2. Setup the Report and Browser manually
        startReport();
        myScraper.openBrowser();

        // 3. Setup the logs
        myScraper.setupTestLogs();

        // 4. Run the actual scraping logic
        myScraper.runScrapingData();

        System.out.println("Scraping completed successfully!");

        // 5. Always close the browser and save the HTML report at the end!
        myScraper.closeBrowser();
        saveReport();
    }

    // This gets called right after the browser opens
    public void setupTestLogs() {
        // 1. Create a log inside the Extent Report for this execution
        testLog = report.createTest("Rediff Scraper Execution");

        // 2. Load the Actions so we can use them
        scraperActions = new RediffActions(browser);

        // 3. Log a simple info message
        testLog.log(Status.INFO, "Website opened successfully");
    }

    // This is the actual Data Scraping logic
    public void runScrapingData() throws Exception {

        // Step 1: Tell the report we are starting
        testLog.log(Status.INFO, "Starting to scrape Headers...");

        // Step 2: Ask Actions class to get all the headers
        List<String> headers = scraperActions.scrapeHeaders();
        testLog.log(Status.INFO, "Successfully scraped " + headers.size() + " Headers");

        // Step 3: Tell the report we are scraping body
        testLog.log(Status.INFO, "Starting to scrape all Data Rows (This may take a minute)...");

        // Step 4: Ask Actions class to get every single row and cell of data
        List<List<String>> allRowsData = scraperActions.scrapeAllRows();
        testLog.log(Status.INFO, "Successfully scraped " + allRowsData.size() + " Data Rows");

        // Step 5: Tell the report we are saving the file
        testLog.log(Status.INFO, "Saving all scraped data into a new Excel file...");

        // Step 6: Ask the Utility class to create the Excel file and fill it with our
        // scraped data
        WriteExcel.saveTableData(headers, allRowsData);

        // Step 7: Take a picture of the screen to prove we did it
        String picture = TakeScreenshot.capture(browser, "ScrapeSuccess");

        // Step 8: Pass the test in the report and attach the picture!
        testLog.pass("Data scraped and saved to testdata/ScrapedStockData.xlsx successfully!",
                MediaEntityBuilder.createScreenCaptureFromPath(picture).build());
    }
}
