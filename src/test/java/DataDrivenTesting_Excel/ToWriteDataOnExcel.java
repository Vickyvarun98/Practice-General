package DataDrivenTesting_Excel;

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

public class ToWriteDataOnExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// Create Object for FIS
		FileInputStream fis=new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		//Workbook 
		Workbook wb=WorkbookFactory.create(fis);
		//Get Sheet
		Sheet sh = wb.getSheet("ORG");
		//Get Row
		Row row = sh.getRow(1);
//		Select the cell index we need to pass data & Set the datatype of cell
//		Cell cell = row.createCell(4);
	//	cell.setCellType(CellType.STRING);
//		To Avoid Deprecation We can use this alsoo
		Cell cell = row.createCell(4, CellType.STRING);
	
		
		//set cell value
		cell.setCellValue("FAIL");
		//wb.getSheet("ORG").getRow(1).createCell(4,CellType.STRING).setCellValue("FAIL");
		
		//To write & store the data
		FileOutputStream fos=new FileOutputStream("./src/test/resources/Test_Data/Test_Case_Data.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("======EXECUTED======");
		
		
		
	}

}
