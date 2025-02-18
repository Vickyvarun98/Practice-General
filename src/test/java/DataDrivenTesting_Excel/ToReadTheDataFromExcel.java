package DataDrivenTesting_Excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadTheDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		String Expected_Data = "TC_03";
		String Data1 = "";
		String Data2 = "";
		String Data3 = "";
		boolean flag = false;
		// To fetch data from Path
		FileInputStream fis = new FileInputStream("./src/test/resources/Test_Data/Test_Case_Data.xlsx");
		// Read the data
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("ORG");
		int rowcount = sheet.getLastRowNum();
		for (int i = 0; i <= rowcount; i++) {
			//String Data = "";
			
			try {
				String Data = sheet.getRow(i).getCell(0).toString();
				if (Data.equals(Expected_Data)) {
					flag = true;
					Data1 = sheet.getRow(i).getCell(1).getStringCellValue();
					Data2 = sheet.getRow(i).getCell(2).toString();
					Data3 = sheet.getRow(i).getCell(3).getStringCellValue();

				}
			} catch (Exception e) {
				
			}

		}
		if (flag == true) {
			System.out.println(Data1);
			System.out.println(Data2);
			System.out.println(Data3);
		} else {
			System.out.println(Expected_Data + " is not found");
		}

		wb.close();
	}

}
