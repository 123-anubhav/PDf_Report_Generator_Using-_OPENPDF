Here's a beautiful, structured `README.md` file for your **PDF Generator** project using Spring Boot, which includes all the necessary details to get started, run, and understand your project:

```markdown
# 📄 PDF Generator Project

A Spring Boot-based application that generates PDF reports from user data stored in a MySQL database. This project uses **OpenPDF** for creating professional PDF documents.

---

## 🚀 Features
- Generate PDF reports with user details.
- Export PDFs with a well-formatted table.
- Download PDFs directly via an HTTP endpoint.

---

## 🛠️ Technologies Used
- **Spring Boot 3.3.5**
- **Java 17**
- **OpenPDF** (PDF generation)
- **MySQL Database** (data storage)
- **Lombok** (for boilerplate code reduction)
- **Maven** (project build tool)

---

## 📁 Project Structure
```bash
Pdf-Generator
├── src/main/java
│   ├── online/experiencedeveloper/dao         # Data Access Layer
│   ├── online/experiencedeveloper/model       # Entity Models
│   ├── online/experiencedeveloper/report      # PDF Generation Service
│   └── online/experiencedeveloper/rest        # REST Controller
├── src/main/resources
│   ├── application.properties                 # Configuration File
├── pom.xml                                    # Maven Dependencies
```

---

## 📦 Dependencies
The project uses the following dependencies:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.github.librepdf</groupId>
        <artifactId>openpdf</artifactId>
        <version>1.3.8</version>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

---

## ⚙️ Configuration

Ensure the following configurations are set in your `application.properties` file:

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🛠️ How to Run the Project

### Prerequisites
- Java 17+
- Maven
- MySQL Database

### Steps to Run
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/Pdf-Generator.git
   cd Pdf-Generator
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the PDF Generation Endpoint**:
   Open your browser and navigate to:
   ```
   http://localhost:8080/pdf
   ```
   This will automatically download the PDF file named `USER-INFO-LIST.pdf`.

---

## 📄 Example Output
The generated PDF includes a table with the following columns:
- **S.NO.**: Serial number
- **User Name**: Name of the user
- **E-mail**: User's email address
- **Password**: Encrypted password

---

## 🛠️ Implementation Details

### PDF Generation Logic
The PDF report is generated using the `ReportService` class, which fetches data from the database and formats it into a table using OpenPDF.

#### Sample Code
```java
@Autowired
public UserInfoRepo users;

public void generatePDF(HttpServletResponse response) throws IOException {
    List<UserInfo> usersItem = users.findAll();
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, response.getOutputStream());
    document.open();

    Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.DARK_GRAY);
    Paragraph p = new Paragraph("List of UsersInfo", font);
    p.setAlignment(Paragraph.ALIGN_CENTER);
    document.add(p);

    PdfPTable table = new PdfPTable(4);
    table.setWidthPercentage(100);
    table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 2.5f});
    table.setSpacingBefore(10);

    for (UserInfo user : usersItem) {
        table.addCell(String.valueOf(user.getId()));
        table.addCell(user.getUserName());
        table.addCell(user.getEmailId());
        table.addCell(user.getPassowrd());
    }
    document.add(table);
    document.close();
}
```

---

## 🔄 API Endpoints

| HTTP Method | Endpoint   | Description                       |
|-------------|------------|-----------------------------------|
| `GET`       | `/pdf`    | Generates and downloads the PDF   |

---

## 🛡️ License
This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author
**Anubhav Srivastava**  

---

## 📞 Contact
For any inquiries or support, please reach out at:
- Email: [anubhav.aa2@gmail.com]

---

## 🙌 Acknowledgements
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [OpenPDF Library](https://github.com/LibrePDF/OpenPDF)
- Special thanks to the Open Source community!

---
