package Practise_Java_Concepts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToPassDataOnExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		// Object Of FIS & FOS
		FileInputStream fis = new FileInputStream("./src/test/resources/Test_Data/Test_Case_Data.xlsx");
		FileOutputStream fos = new FileOutputStream("./src/test/resources/Test_Data/Test_Case_Data.xlsx");
		// To Read the Data
		Workbook wb = WorkbookFactory.create(fis);
		// Object of sheet
		Sheet sheet = wb.getSheet("List");
		// Row
		Row row = sheet.getRow(1);
		// fetch for cell
		Cell cell = row.createCell(2);
		// Set datatype
		cell.setCellType(CellType.STRING);
		// set cell value
		cell.setCellValue("PASS");
		
		
		wb.write(fos);
		wb.close();
		System.out.println("Data is passed.....");

	}

}
