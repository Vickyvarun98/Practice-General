package DataDrivenTesting_Excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToCheckStudentsArePassOrFail {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("List");
		int Last_Row = sheet.getLastRowNum();
		for (int i = 1; i <= Last_Row; i++) {
			Row row = sheet.getRow(i);
			int Marks = (int) row.getCell(2).getNumericCellValue();
			FileOutputStream fos = new FileOutputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
			System.out.println(Marks);
			if (Marks >= 35) {

				Cell cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("PASS");

			} else {

				Cell cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("FAIL");
			}
			wb.write(fos);
		}
		wb.close();

		System.out.println("Test Script Executed...");

	}

}
