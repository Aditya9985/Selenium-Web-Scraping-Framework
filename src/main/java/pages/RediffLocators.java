package pages;

import org.openqa.selenium.By;

public class RediffLocators {

    // 1. Locator for the main container of the table
    public static final By DATA_TABLE = By.className("dataTable");

    // 2. Locator to find all the Header columns (Company, Group, Prev Close, etc.)
    public static final By TABLE_HEADERS = By.xpath("//table[@class='dataTable']/thead/tr/th");

    // 3. Locator to find every single Row in the table body
    public static final By TABLE_ROWS = By.xpath("//table[@class='dataTable']/tbody/tr");

    // 4. Locator to find the individual Data Cells inside a specific Row
    public static final By ROW_CELLS = By.tagName("td");

}
