package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	static String path ;
	
	public ExcelUtility (String path) {
		this.path = path;
	}
	
	
	public int getRowCount(String xlsheet) throws IOException {
		fis = new  FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fis.close();
		return rowcount;
		
	}
	
	public int getCellCount(String xlsheet, int rownum) throws IOException {
		fis = new  FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fis.close();
		return cellcount;
		
	}
	
	public String getCellData(String xlsheet, int rownum, int cellnum) throws IOException {
		fis = new  FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell = row.getCell(cellnum);
		String data;
		//String data = cell.toString();
		try {
		DataFormatter formatter = new DataFormatter();
		data = formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data ="";
		}		
		
		wb.close();
		fis.close();
		return data;
		
	}
	
	public void setCellData(String xlsheet, int rownum, int cellnum,String data) throws IOException {
		
		File xlfile=  new File(path);
		
		if(!xlfile.exists()) {
			wb = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			wb.write(fos);
		}
				
		fis = new  FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		
		if(wb.getSheetIndex(xlsheet)==-1)	{  //if sheet not exist
			wb.createSheet(xlsheet);
		}
		
		ws = wb.getSheet(xlsheet);
		
		if(ws.getRow(rownum)==null)	{    // if row not exist
			ws.createRow(rownum);
		}
		
		row=ws.getRow(rownum);
		cell = row.createCell(cellnum);
		cell.setCellValue(data);	
		fos = new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();		
		
	}
	
	public void fillGreenColorToCell(String xlsheet, int rownum, int cellnum) throws IOException {
		fis = new  FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell = row.getCell(cellnum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);	
		
		fos = new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
		
	}
	
	public  void fillRedColorToCell(String xlsheet, int rownum, int cellnum) throws IOException {
		fis = new  FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell = row.getCell(cellnum);
		
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fos = new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
		
	}



}
