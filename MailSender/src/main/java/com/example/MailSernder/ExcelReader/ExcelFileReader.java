package com.example.MailSernder.ExcelReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.MailSernder.ServiceImpl.EmailServiceImpl;

@Service
public class ExcelFileReader {

		
	
	 private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExcelFileReader.class);
	
	 public Map<String, Double> readDataFromExcel(String filePath) throws IOException {
	        Map<String, Double> dataMap = new HashMap<>();

	        FileInputStream fileInputStream = new FileInputStream(filePath);
	        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        for( int i = 0 ; i<3 ; i++) {
	        Row row = sheet.getRow(3);
	        	Cell cell = row.getCell(i);
	        	String cellValue = cell.getStringCellValue();
	        Row row1=sheet.getRow(4);
	        	Cell Cell1 = row1.getCell(i);
	        
	        	double numericCellValue = Cell1.getNumericCellValue();  
	        	dataMap.put(cellValue, numericCellValue);
	        }			
	        workbook.close();
	        return dataMap;
	    }
	
	
}
