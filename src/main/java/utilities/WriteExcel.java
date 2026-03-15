package utilities;

import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

    public static void saveTableData(List<String> headers, List<List<String>> allRowsData) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Stocks");
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {//increase roww if filled
            headerRow.createCell(i).setCellValue(headers.get(i));
        }

        int currentRowNumber = 1;//0 taken by headers
        for (List<String> rowData : allRowsData) {
            XSSFRow excelRow = sheet.createRow(currentRowNumber);

            for (int col = 0; col < rowData.size(); col++) {
                excelRow.createCell(col).setCellValue(rowData.get(col));
            }
            currentRowNumber++; // Move to the next row in Excel
        }

        FileOutputStream fileOut = new FileOutputStream("testdata/ScrapedStockData.xlsx");

        workbook.write(fileOut);
        workbook.close();
        fileOut.close();
    }
}
