package online.experiencedeveloper.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import online.experiencedeveloper.report.ReportService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	public ReportService reportService;
	
	 // Endpoint for PDF report generation
    @GetMapping("/pdf")
    public void generatePDFReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=USER-INFO-LIST.pdf";
        response.setHeader(headerKey, headerValue);
        reportService.generatePDF(response);
    }

}
