




# Rediff Web Scraper - Interview Preparation Guide

This guide explains **every single part** of your new web scraping automation framework in basic, beginner-friendly English. 

---

## 1. Project Structure overview
When someone asks: *"How did you structure your web scraper?"*
**Your Answer:** "I used the exact same Page Object Model (POM) design pattern as my login framework, but adapted it for extracting data instead of just logging in.
1. **utilities**: Helper classes to read configs, take screenshots, setup reports, and a brand new `WriteExcel` class to save the scraped data.
2. **pages**: Stores the web elements (the coordinates of the HTML table).
3. **actions**: Loops through the HTML table and extracts the text.
4. **base & tests**: Handles opening the browser, running the scrape, and saving the file."

---

## 2. Root Files & Folders

### File: `pom.xml`
**What does it do?** It tells Maven to download Selenium, JUnit, ExtentReports, and Apache POI.

### File: `config.properties`
**What does it do?** It stores the `browser` and the `url` for the Rediff Gainers page (`https://money.rediff.com/gainers/bse/daily/groupall`).
- *If an interviewer asks:* "I put the URL in a properties file so that if the website address ever changes, I just edit one line of text here instead of digging through my Java code."

### Folder: `testdata/`
**What does it do?** Unlike our login framework which *read* from an Excel file here, our new scraper will *create* a brand new file here called `ScrapedStockData.xlsx` to save the results.

---

## 3. Package: `utilities`

### Class: `WriteExcel.java`
**What does it do?** It takes the data we scraped using Selenium, creates a brand new Excel file in the computer's memory, writes the data into rows and columns, and then physically saves the file to the hard drive.
- **`XSSFWorkbook workbook = new XSSFWorkbook();`**: Creates a completely new, blank Excel file.
- **`XSSFSheet sheet = workbook.createSheet("Stocks");`**: Creates a new tab at the bottom called "Stocks".
- **`XSSFRow headerRow = sheet.createRow(0);`**: Creates the very first row (Row 0) at the top of the Excel file for our column titles.
- **`FileOutputStream fileOut = new FileOutputStream(...)`**: This is the command that chooses the folder on your Mac to physically save the file.

### Classes: `ReadConfig`, `TakeScreenshot`, `ExtentSetup`
**What do they do?** These are exactly the same simple helper classes from your other framework! (They read the properties file, take `.png` pictures, and create `.html` reports).

---

## 4. Package: `pages`

### Class: `RediffLocators.java`
**What does it do?** It stores the "GPS coordinates" (locators) for the big data table on the website.
- **`By TABLE_HEADERS = By.xpath("//table[@class='dataTable']/thead/tr/th");`**: 
  - *If an interviewer asks:* "I used XPath to write a custom path. It tells Selenium to find the table with the specific class name, go inside the `thead` (Table Head), into the `tr` (Table Row), and grab all the `th` (Table Header) elements."
- **`By TABLE_ROWS = By.xpath("//table[@class='dataTable']/tbody/tr");`**: Same logic as above, but this points to the `tbody` (Table Body) where all the actual stock data lives.
- **`By ROW_CELLS = By.tagName("td");`**: This is a simple locator to find the individual boxes (cells) inside a specific row.

---

## 5. Package: `actions`

### Class: `RediffActions.java`
**What does it do?** This is the core logic of the scraper. It uses the locators to actually read the website.
- **`public List<String> scrapeHeaders()`**: Finds all the column names (Company, Group, Prev Close) and saves them into a Java List.
- **`public List<List<String>> scrapeAllRows()`**: This looks scary but is very simple! It is a "List of Lists" (A big list holding many small lists).
  - *If an interviewer asks how it works:* "First, I find all 100+ rows on the website. Then, I use a `for` loop to go through each row one-by-one. Inside that loop, I find all the individual cells (`td`) for that specific row. I extract the text from the cells, put them into a small 'singleRowData' list, and then add that small list into my massive 'allRowsData' list."

---

## 6. Package: `base` and `tests`

### Class: `BaseTest.java` (Inside `base` package)
**What does it do?** Just like before, this handles opening Chrome, waiting for elements, and closing Chrome so our Test class doesn't have to worry about it.

### Class: `RediffScraperTest.java` (Inside `tests` package)
**What does it do?** This is the main script that glues everything together step-by-step.
- **`scraperActions.scrapeHeaders();`**: Step 1 - Get the column titles.
- **`scraperActions.scrapeAllRows();`**: Step 2 - Get all the stock data.
- **`WriteExcel.saveTableData(headers, allRowsData);`**: Step 3 - Pass the data to our utility class to write the Excel file.
- **`TakeScreenshot.capture(...)`**: Step 4 - Take a picture to prove we did it!


---
### Interview Cheat Sheet:
1. **Web Scraping**: The act of using code (like Selenium) to automatically read and extract text from a website.
2. **List of Lists (`List<List<String>>`)**: The best way to store table data in Java. The "Outer" list represents the Rows, and the "Inner" lists represent the Columns (cells) inside that row.
3. **Apache POI**: The Java library we use to create and edit Microsoft Excel `.xlsx` files without needing Excel installed.
4. **XPath**: We used exact, custom XPath directions to safely find the specific rows and columns of the `dataTable` inside the raw HTML.
