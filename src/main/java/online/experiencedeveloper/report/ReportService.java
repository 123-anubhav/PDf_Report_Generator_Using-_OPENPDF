package online.experiencedeveloper.report;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import online.experiencedeveloper.dao.UserInfoRepo;
import online.experiencedeveloper.model.UserInfo;

@Service
public class ReportService {

	@Autowired
	public UserInfoRepo users;

	// Method to generate PDF
	public void generatePDF(HttpServletResponse response) throws IOException {

		List<UserInfo> usersItem = users.findAll();
		System.out.println(usersItem);

		// CREATE A DOCUMENT
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		// SETTING A FONT,SIZE,COLOR OF DOCUMENT
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.DARK_GRAY);

		// SETTING A PARAGRAPH IN DOCUMENT AND ALIGNMENT,COLOR
		Paragraph p = new Paragraph("List of UsersInfo", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		// ADD A PARAGRAPH IN DOCUMENT
		document.add(p);

		// CREATE A TABLE FOR DOCUMENT WITH TOTAL COLUMN NUMBER
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f,2.5F });
		table.setSpacingBefore(10);

		// SETTING PDF HEADER CELL BACKGROUND-COLOR
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.DARK_GRAY);
		cell.setPadding(5);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("S.NO.", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("User Name", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("PASSWORD", font));
		table.addCell(cell);

		// SETTING PDF DATA
		for (UserInfo user : usersItem) {
			table.addCell(String.valueOf(user.getId()));
			table.addCell(user.getUserName());
			table.addCell(user.getEmailId());
			table.addCell(user.getPassowrd());
		}

		// ADDING A TABLE DATA TO DOCUMENT
		document.add(table);

		// CLOSE A DOCUMENT IS NECESSSARY OTHERWISE PDF WILL CORRUPT AND IT WILL NOT OPEN
		document.close();
	}
}
