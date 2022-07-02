import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.zip.ZipFile;


public class TestZipInResources {

    ClassLoader classLoader = TestZipInResources.class.getClassLoader();

    @DisplayName("csv in zip")
    @Test
    void csvTest() throws Exception {

        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("NewZip.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("User3.csv");
        List<String[]> list;
        InputStream inputStream = zipFile.getInputStream(entry);
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
        list = csvReader.readAll();
        assertThat(list).contains(
                new String[] {"Username; Identifier;First name;Last name"},
                new String[] {"booker12;9012;Rachel;Booker"});

                }



    @DisplayName("pdf in zip")
    @Test
    void pdfTest() throws Exception {

        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("NewZip.zip")).getFile());

        ZipEntry entry = zipFile.getEntry("sample.pdf");
        PDF pdf;
        InputStream inputStream = zipFile.getInputStream(entry);
        pdf = new PDF(inputStream);
        assertThat(pdf.text).contains("A Simple PDF File");

    }

    @DisplayName("xlsx in zip")
    @Test
    void checkXLSTest() throws Exception {
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("NewZip.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("sample2.xlsx");
        XLS xlsx;
        InputStream inputStream = zipFile.getInputStream(entry);
        xlsx = new XLS(inputStream);
        assertThat(xlsx.excel.getSheetAt(0)
                .getRow(3)
                .getCell(6)
                .getStringCellValue())
                .contains("Mary Maryson");
    }
}