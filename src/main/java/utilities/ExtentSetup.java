package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentSetup {

    // This creates our Report file
    public static ExtentReports setupReport() {
      ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        spark.config().setDocumentTitle("Rediff Scraper Report");
        spark.config().setReportName("Stock Scraping Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);

        return extent;
    }
}
