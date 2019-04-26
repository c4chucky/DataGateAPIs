    package mypackage
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path
import java.nio.file.Paths
import java.text.DateFormat
import java.text.SimpleDateFormat
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Date
import com.kms.katalon.core.configuration.RunConfiguration

public class WriteExcel {


	@Keyword
	public void demoKey(String result, int rowNumber, int column) throws IOException{

		String projectDir = RunConfiguration.getProjectDir()
		Path sourceFile = Paths.get(projectDir + "/Documents/SaveSale.xlsx");
		//Path target = Paths.get(projectDir + "/Documents/SaveSale.xlsx");

		/*		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd.hhmmss");
		 Date date = new Date();
		 String projectDir = RunConfiguration.getProjectDir()
		 Path source = Paths.get(projectDir + "/Documents/SaveSale.xlsx");
		 Path target = Paths.get(projectDir + "/Documents/Processed/SaveSale" + sdf.format(date) + ".xlsx");
		 */
		FileInputStream fis = new FileInputStream("C:/Automation Tests/Automation - Data Gate/Documents/SaveSale.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("SaveSale");
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.createCell(7);

		cell.setCellType(cell.CELL_TYPE_STRING);
		cell.setCellValue(result);
		FileOutputStream fos = new FileOutputStream("C:/Automation Tests/Automation - Data Gate/Documents/SaveSale.xlsx");
		workbook.write(fos);
		fos.close();
	}



	@Keyword
	public void writeToDocument(String result, String documentName, String sheetName, int rowNumber, int column) throws IOException{

		String projectDir = RunConfiguration.getProjectDir()
		Path sourceFile = Paths.get(projectDir + "/Documents/SaveSale.xlsx");
		//Path target = Paths.get(projectDir + "/Documents/SaveSale.xlsx");

		/*		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd.hhmmss");
		 Date date = new Date();
		 String projectDir = RunConfiguration.getProjectDir()
		 Path source = Paths.get(projectDir + "/Documents/SaveSale.xlsx");
		 Path target = Paths.get(projectDir + "/Documents/Processed/SaveSale" + sdf.format(date) + ".xlsx");
		 */
		FileInputStream fis = new FileInputStream("C:/Automation Tests/Automation - Data Gate/Documents/"+documentName+".xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.createCell(column);

		cell.setCellType(cell.CELL_TYPE_STRING);
		cell.setCellValue(result);
		FileOutputStream fos = new FileOutputStream("C:/Automation Tests/Automation - Data Gate/Documents/"+documentName+".xlsx");
		workbook.write(fos);
		fos.close();
	}
}

