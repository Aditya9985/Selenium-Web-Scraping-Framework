package actions;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.RediffLocators;

public class RediffActions {

    WebDriver browser;

    public RediffActions(WebDriver myBrowser) {
        this.browser = myBrowser;
    }

    // This method gets the top header titles of the table
    public List<String> scrapeHeaders() throws Exception {

        List<String> headerTextList = new ArrayList<>();

        // 1. Find all the header elements on the page
        List<WebElement> headerElements = browser.findElements(RediffLocators.TABLE_HEADERS);

        // 2. Extract the text from each element and add it to our list
        for (WebElement header : headerElements) {
            String text = header.getText();
            headerTextList.add(text);
        }

        return headerTextList;
    }

    // This method gets every single row and cell of data
    public List<List<String>> scrapeAllRows() throws Exception {

        // This big list will hold many small lists (one small list for each row)
        List<List<String>> allRowsData = new ArrayList<>();

        // 1. Find all the rows in the table
        List<WebElement> rowElements = browser.findElements(RediffLocators.TABLE_ROWS);

        // 2. Loop through every single row
        for (WebElement row : rowElements) {

            // Create a small list just for this row's data
            List<String> singleRowData = new ArrayList<>();

            // 3. Find the individual cells (columns) INSIDE this specific row
            List<WebElement> cells = row.findElements(RediffLocators.ROW_CELLS);

            // 4. Extract the text from each cell and add it to the small list
            for (WebElement cell : cells) {
                singleRowData.add(cell.getText());
            }

            // 5. Add the finished row data to the big main list
            allRowsData.add(singleRowData);
        }

        return allRowsData;
    }
}
