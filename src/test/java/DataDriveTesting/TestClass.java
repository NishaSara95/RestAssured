package DataDriveTesting;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class TestClass {
	
	
	public static void main(String[] args) throws IOException, InvalidFormatException {
		
		ExcelDataDriven excelData = new ExcelDataDriven();
		ArrayList<Object> data = excelData.getData("Payment");
		System.out.println(data);
		
	
	}
	
}
