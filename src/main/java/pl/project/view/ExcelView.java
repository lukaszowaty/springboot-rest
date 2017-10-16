package pl.project.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

//import com.itextpdf.text.Font;

import pl.project.model.Car;

public class ExcelView  extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// set the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"report-cars.xls\"");
		
		
		@SuppressWarnings("unchecked")
		List<Car> cars = (List<Car>) model.get("cars");
		
		// create new excel sheet
		Sheet sheet = workbook.createSheet("Cars detail");
		sheet.setDefaultColumnWidth(30);
		
		// create font and style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
		
        // create header name row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Car brand");
        header.getCell(0).setCellStyle(style);
        
        header.createCell(1).setCellValue("Car model");
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue("Date production");
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue("Car mileage");
        header.getCell(3).setCellStyle(style);
        
        int rowCount = 1;
        
        for(Car car : cars) {
        	Row carRow = sheet.createRow(rowCount++);
        	carRow.createCell(0).setCellValue(car.getBrand());
        	carRow.createCell(1).setCellValue(car.getModel());
        	carRow.createCell(2).setCellValue(car.getDateProduction());
        	carRow.createCell(3).setCellValue(car.getCarMileage());
        }
        
	}

}
