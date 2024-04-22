package DataDriveTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelDataDriven {

	@Test
	public ArrayList<Object> getData(String data) throws IOException, InvalidFormatException {

		// Input file to get access

		File inputFile = new File(getResourcePath("/src/main/resources/DataDrivenTest.xlsx"));
		FileInputStream inputStream = new FileInputStream(inputFile);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		ArrayList<Object> list = new ArrayList<Object>();

		int count = workbook.getNumberOfSheets();

		for (int i = 0; i < count; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase("TestData")) {

				XSSFSheet sheet = workbook.getSheetAt(i);
				// Sheet is the collection of rows
				Iterator<Row> rows = sheet.iterator();
				// Row is the collection of cells
				Row firstrow = rows.next();
				Iterator<Cell> cells = firstrow.cellIterator();
				int k = 0;
				int column = 0;
				while (cells.hasNext()) {
					Cell cell = cells.next();

					if (cell.getStringCellValue().equalsIgnoreCase("TestCases")) {
						column = k;

					}
					k++;

				}
				System.out.println(column);
				while (rows.hasNext()) {

					Row row = rows.next();
					if (row.getCell(column).getStringCellValue().equalsIgnoreCase(data)) {
						Iterator<Cell> cellIterator = row.cellIterator();
						while (cellIterator.hasNext()) {
							list.add(cellIterator.next().getStringCellValue());

						}

					}
				}

			}

		}
		return list;

	}

	public static String getResourcePath(String path) {

		String basePath = System.getProperty("user.dir");
		return basePath + path;
	}

}
