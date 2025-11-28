package bridgelabz.UtilityClases;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviderUtility {
	
	@DataProvider(name = "logindata")
	public static Object[][] getData() {
	    return DataProviderUtility.getTestData(
	            "./testdata/Open_Cart_Login_Data_Driven_Excel_Data.xlsx",
	            "login"
	    );
	}
	
	public static Object[][] getTestData(String excelPath, String sheetName) {
		Object[][] data = null;

		try {
			
			XSSFWorkbook wb = ExcelUtility.openExcel(excelPath);
			Sheet sheet = ExcelUtility.selectSheet(sheetName);
			
			int totalRows = ExcelUtility.getRowCount();
			int totalCols = ExcelUtility.getColumnCount(0);
			
			data = new Object[totalRows][totalCols];

			for (int i = 1; i <= totalRows; i++) { // Skip header
				Row row = sheet.getRow(i);
				for (int j = 0; j < totalCols; j++) {
					Cell cell = row.getCell(j);
					data[i - 1][j] = getCellValue(cell);
				}
			}
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	private static Object getCellValue(Cell cell) {
		if (cell == null)
			return "";

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return cell.getNumericCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case FORMULA:
			return cell.getCellFormula();
		default:
			return "";
		}
	}
}
