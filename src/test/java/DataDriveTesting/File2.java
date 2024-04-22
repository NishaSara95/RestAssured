package DataDriveTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class File2 {
	
	@Test
	public void dataDriven() throws IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Windows 11\\Downloads\\DataDrivenTest.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase("TestData")){
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Sheets are collection of rows
				Iterator<Row> rows = sheet.iterator();
				while(rows.hasNext())
				{
					Row row = rows.next();
					Iterator<Cell> cells = row.cellIterator();
					while(cells.hasNext()) {
						Cell cell = cells.next();
						if(cell.getStringCellValue().equalsIgnoreCase("TestData2")) {
							int columnIndex = cell.getColumnIndex();
							System.out.println(columnIndex);
							
						}
					}
				}
				
			}
			
			
			
		}
		
	}

}
