package pl.project.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pl.project.model.Car;

public class MyPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Car> cars = (List<Car>) model.get("cars");
		document.add(new Paragraph("Generated cars list " + LocalDate.now()));
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);
		
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);
        
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);
        
        // write table header
        cell.setPhrase(new Phrase("Car brand", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Car model", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Date production", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Car mileage", font));
        table.addCell(cell);
        
        
        for (Car car : cars) {
        	table.addCell(car.getBrand());
        	table.addCell(car.getModel());
        	table.addCell(car.getDateProduction());
        	table.addCell(String.valueOf(car.getCarMileage()));
        }
        
        document.add(table);
        
	}

}
