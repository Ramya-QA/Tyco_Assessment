package Selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utilities {
		
//Function to Read config file properties 	
	public String getPropertyData(String key) throws IOException {		
		
    //String FILE_NAME = "C:\\Users\\SUMANTH\\workspacemarch16\\AutomatePost\\src\\config.properties";
    String FILE_NAME = (System.getProperty("user.dir")+"\\src\\config.properties");
	Properties config = new Properties();
	FileInputStream cfile = new FileInputStream(FILE_NAME);	
	config.load(cfile);
	
	return config.getProperty(key);
	
	
	}		
	
		
	
//Function to find element based on object locator	
	public void type(By objLocator, String sTestdata, WebDriver driver)
			throws Throwable {
		

			
		driver.findElement(objLocator).clear();
		driver.findElement(objLocator).sendKeys(sTestdata);

	}
	
//function to read data from excel	
	public Object[][] getRowData(String sheetName,  String path)
	{
		String cellData = null;
		//String RowData = null;
		
		Object [][] testdata = new Object[2][7];
		
		int iRowCount = 0;
		int iCellCount = 0;
		
		try {
			//path = "C:\\Users\\SUMANTH\\workspacemarch16\\AutomatePost\\TestData\\TestData.xlsx";
			
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();				
				
		if (iRowCount > 0){			
				Iterator<Cell> cellIterator = row.cellIterator();				
				while (cellIterator.hasNext()) {					
					Cell cell = cellIterator.next();
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							//cellData=sheet.getRow(1).getCell(colnum).toString();
							//cellData = cell.getStringCellValue();
							cellData = sheet.getRow(iRowCount).getCell(iCellCount).toString();
							break;
						case Cell.CELL_TYPE_STRING:
							/*cellData=sheet.getRow(1).getCell(colnum).toString();*/
							cellData = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_BLANK:
							try{
								System.out.println("Blank space");
							}catch (Exception e) {
								e.printStackTrace();
							}//end of Try Catch							
							break;
						}
					testdata[iRowCount-1][iCellCount] = cellData;		
					iCellCount++;
							
						
					//}//end of for loop
				}//end of while loop
				
		}//end of if rowcount >1
		iRowCount++;
		iCellCount = 0;
			}
			workbook.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//returns the complete column data
		//return cellData;
		return testdata;
	}
}
