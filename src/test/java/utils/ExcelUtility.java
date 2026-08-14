package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    FileInputStream fis;
    FileOutputStream fos;
    XSSFWorkbook wb;
    XSSFSheet sheet;

    // Store the Excel file path
    String filePath;


    // Constructor
    public ExcelUtility(String fileName, String sheetName) throws IOException {

        filePath = System.getProperty("user.dir")
                + "/src/test/resources/" + fileName;

        fis = new FileInputStream(filePath);

        wb = new XSSFWorkbook(fis);

        sheet = wb.getSheet(sheetName);

        // Important:
        // Close input stream after loading workbook.
        // This prevents file-locking problems when writing.
        fis.close();
        fis = null;
    }


    // Read Excel Data
    public Object[][] getExcelData(int totalColumns) {

        int rows = sheet.getLastRowNum();

        Object[][] data = new Object[rows][totalColumns];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= rows; i++) {

            Row row = sheet.getRow(i);

            for (int j = 0; j < totalColumns; j++) {

                if (j == 0) {

                    data[i - 1][j] =
                            Integer.parseInt(
                                    formatter.formatCellValue(
                                            row.getCell(j)
                                    )
                            );

                } else {

                    data[i - 1][j] =
                            formatter.formatCellValue(
                                    row.getCell(j)
                            );
                }
            }
        }

        return data;
    }


    // Write Actual Result and Status
    public void writeResult(
            int rowNo,
            String actualResult,
            String status) throws IOException {

        Row headerRow = sheet.getRow(0);

        int expectedColumn = -1;

        // Find "Expected Result" column
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {

            Cell cell = headerRow.getCell(i);

            if (cell != null &&
                cell.getStringCellValue()
                    .trim()
                    .equalsIgnoreCase("Expected Result")) {

                expectedColumn = i;
                break;
            }
        }


        if (expectedColumn == -1) {

            throw new IOException(
                    "Expected Result column not found in Excel"
            );
        }


        int actualResultColumn = expectedColumn + 1;

        int statusColumn = actualResultColumn + 1;


        Row row = sheet.getRow(rowNo);

        if (row == null) {

            row = sheet.createRow(rowNo);
        }


        // Actual Result Cell
        Cell actualCell = row.getCell(actualResultColumn);

        if (actualCell == null) {

            actualCell =
                    row.createCell(actualResultColumn);
        }

        actualCell.setCellValue(actualResult);


        // Status Cell
        Cell statusCell = row.getCell(statusColumn);

        if (statusCell == null) {

            statusCell =
                    row.createCell(statusColumn);
        }

        statusCell.setCellValue(status);


        // Write back to the SAME Excel file
        fos = new FileOutputStream(filePath);

        wb.write(fos);

        fos.flush();

        fos.close();

        fos = null;
    }


    // Close Workbook
    public void closeExcel() throws IOException {

        if (fos != null) {

            fos.close();

            fos = null;
        }

        if (wb != null) {

            wb.close();

            wb = null;
        }

        if (fis != null) {

            fis.close();

            fis = null;
        }
    }
}