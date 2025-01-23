package utilities;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    static FileInputStream fis = null; // File input stream

    public static FileInputStream getFis() throws FileNotFoundException { // Method to get the file input stream

        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\BookingData.xlsx"; // Get the file path
        File file = new File(filePath); // Create a file object

        fis = new FileInputStream(file); // Create a file input stream
        return fis; // Return the file input stream
    }

    public Object[][] getExcelData() throws IOException {

        fis = getFis(); // Get the file input stream
        XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create a workbook object
        XSSFSheet sheet = workbook.getSheetAt(0); // Get the first sheet

        int totalRowCount = sheet.getLastRowNum(); // Get the total number of rows
        int totalColCount = sheet.getRow(0).getLastCellNum(); // Get the total number of columns

        String[][] arrayExcelData = new String[totalRowCount][totalColCount]; // Create a 2D array to store the data

        for (int i = 1; i <= totalRowCount; i++) { // Loop through the rows
            for (int j = 0; j < totalColCount; j++) { // Loop through the columns

                XSSFRow row = sheet.getRow(i); // Get the row
                arrayExcelData[i-1][j] = sheet.getRow(i).getCell(j).toString(); // Get the cell value and store it in the array
               // System.out.println("Value at row " + i + ", column " + j + ": " + sheet.getRow(i).getCell(j));  // Print the value

            }
        }
        workbook.close(); // Close the workbook
        return arrayExcelData; // Return the array


    }
}


