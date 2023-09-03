package org.example.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import java.io.FileInputStream;

public class UtilExcel {

    // read the TestData.xlsx file using apache poi

    static Workbook book;
    static Sheet sheet;

    // user.dir is universal and work on all machines
    public static String SHEET_NAME_PATH = System.getProperty("user.dir")+ "/src/test/java/org/example/Resources/TestData.xlsx";
    // direct Absolute path//"C:\\Users\\gaura\\IdeaProjects\\RestANotes\\src\\test\\java\\org\\example\\Resources\\TestData.xlsx";


    public static Object[][] getTestData(String sheetName) throws Exception{
        //FileInputStream file = null;
        FileInputStream file = new FileInputStream(SHEET_NAME_PATH);
        book = WorkbookFactory.create(file);
        sheet = book.getSheet(sheetName);

        //Open the workbook, read the sheet
        //read row and column and
        // put them in getData to return as Object[][]

        System.out.println("Data from excel sheet");
        System.out.println("Rows -> "+ sheet.getLastRowNum());
        System.out.println("Columns -> "+ sheet.getRow(0).getLastCellNum());
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum() ; i++){
            for (int j = 0; j<sheet.getRow(0).getLastCellNum();j++){
                data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }
        }

        return data;
//        return new Object[][]{
//                new Object[] {"admin","admin"},
//                new Object[] {"admin","password"},
//                new Object[] {"admin","password123"},
//                new Object[] {"admin","123"}
//        };
    }

    //@DataProvider
    public Object[][] getData() throws Exception {
        return getTestData("Sheet1");
    }
}
