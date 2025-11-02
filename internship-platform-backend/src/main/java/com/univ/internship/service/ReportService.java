package com.univ.internship.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.univ.internship.model.Grade;
import com.univ.internship.repo.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final GradeRepository gradeRepo;

    public byte[] exportGradesExcel() {
        List<Grade> grades = gradeRepo.findAll();
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("成绩");
            int r = 0;
            Row head = sheet.createRow(r++);
            head.createCell(0).setCellValue("ApplicationID");
            head.createCell(1).setCellValue("Attendance");
            head.createCell(2).setCellValue("Log");
            head.createCell(3).setCellValue("Company");
            head.createCell(4).setCellValue("Teacher");
            head.createCell(5).setCellValue("Final");

            for (Grade g : grades) {
                Row row = sheet.createRow(r++);
                row.createCell(0).setCellValue(g.getApplication().getId());
                row.createCell(1).setCellValue(g.getAttendanceScore() == null ? 0 : g.getAttendanceScore());
                row.createCell(2).setCellValue(g.getLogScore() == null ? 0 : g.getLogScore());
                row.createCell(3).setCellValue(g.getCompanyScore() == null ? 0 : g.getCompanyScore());
                row.createCell(4).setCellValue(g.getTeacherScore() == null ? 0 : g.getTeacherScore());
                row.createCell(5).setCellValue(g.getFinalScore() == null ? 0 : g.getFinalScore());
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            wb.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] exportGradesPdf() {
        List<Grade> grades = gradeRepo.findAll();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document doc = new Document();
            PdfWriter.getInstance(doc, out);
            doc.open();
            doc.add(new Paragraph("实习成绩报表"));
            for (Grade g : grades) {
                doc.add(new Paragraph("ApplicationID: " + g.getApplication().getId() +
                        " Final: " + g.getFinalScore()));
            }
            doc.close();
            return out.toByteArray();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
