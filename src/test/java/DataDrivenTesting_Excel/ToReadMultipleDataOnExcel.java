package DataDrivenTesting_Excel;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadMultipleDataOnExcel {

	public static void main(String[] args) throws IOException {
		
		//Get Excel Path Location by creating Object of FileInputStream 
		FileInputStream fis= new FileInputStream("./src/test/resources/Test_Data/Test_Case_Data.xlsx");
		//Get the workbook in 
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("List");
		int Rows = sheet.getLastRowNum();
		System.out.println(Rows);
		for (int i = 1; i <=Rows; i++) {
			Row row = sheet.getRow(i);
			String Name = row.getCell(0).getStringCellValue();
			String Reg_No = row.getCell(1).getStringCellValue();
			System.out.println(Name+"\t"+Reg_No);
		}
		wb.close();
		
		
		
		
	}

}
