package bridgelabz.UtilityClases;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	private static DataFormatter formatter = new DataFormatter();

	// ========== OPEN EXCEL FILE ==========
	public static XSSFWorkbook openExcel(String filePath) throws IOException {
		fi = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fi);
		fi.close();
		return wb;
	}

	// ========== SAVE & CLOSE EXCEL FILE ==========
	public static void saveAndClose(String filePath) throws IOException {
		fo = new FileOutputStream(filePath);
		wb.write(fo);
		wb.close();
		fo.close();
	}

	// ========== SELECT SHEET ==========
	public static Sheet selectSheet(String sheetName) {
		return sheet = wb.getSheet(sheetName);
	}

	// ========== ROW COUNT ==========
	public static int getRowCount() {
		return sheet.getLastRowNum();
	}

	// ========== COLUMN COUNT ==========
	public static int getColumnCount(int rowNum) {
		XSSFRow row = sheet.getRow(rowNum);
		if (row == null) return 0;
		return row.getLastCellNum();
	}

	// ========== READ DATA ==========
	public static String getCellData(int rowNum, int colNum) {
		XSSFRow row = sheet.getRow(rowNum);
		if (row == null) return "";

		XSSFCell cell = row.getCell(colNum);
		if (cell == null) return "";

		return formatter.formatCellValue(cell);
	}

	// ========== WRITE DATA ==========
	public static void setCellData(int rowNum, int colNum, String data) {
		XSSFRow row = sheet.getRow(rowNum);
		if (row == null)
			row = sheet.createRow(rowNum);

		XSSFCell cell = row.getCell(colNum);
		if (cell == null)
			cell = row.createCell(colNum);

		cell.setCellValue(data);
	}

	// ========== GREEN COLOR ==========
	public static void fillGreenColor(int rowNum, int colNum) {
		fillColor(rowNum, colNum, IndexedColors.GREEN);
	}

	// ========== RED COLOR ==========
	public static void fillRedColor(int rowNum, int colNum) {
		fillColor(rowNum, colNum, IndexedColors.RED);
	}

	private static void fillColor(int rowNum, int colNum, IndexedColors color) {
		XSSFRow row = sheet.getRow(rowNum);
		if (row == null) row = sheet.createRow(rowNum);

		XSSFCell cell = row.getCell(colNum);
		if (cell == null) cell = row.createCell(colNum);

		XSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(color.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
	}


	public static Object[][] getTestData(String excelPath, String sheetName) {
		Object[][] data = null;

		try {
			fi = new FileInputStream(excelPath);
			wb = new XSSFWorkbook(fi);
			sheet = wb.getSheet(sheetName);

			int totalRows = getRowCount();
			int totalCols = sheet.getRow(0).getLastCellNum();

			data = new Object[totalRows - 1][totalCols];

			for (int i = 1; i < totalRows; i++) { // Skip header
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
