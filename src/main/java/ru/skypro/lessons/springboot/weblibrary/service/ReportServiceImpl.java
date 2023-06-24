package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportsDTO;
import ru.skypro.lessons.springboot.weblibrary.exception.ApiException;
import ru.skypro.lessons.springboot.weblibrary.model.Report;
import ru.skypro.lessons.springboot.weblibrary.repository.ReportRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Service
public class ReportServiceImpl implements ReportService {
    private final ObjectMapper objectMapper;
    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ObjectMapper objectMapper, ReportRepository reportRepository) {
        this.objectMapper = objectMapper;
        this.reportRepository = reportRepository;
    }

    @Override
    public int formReportAndGetId() {
        ReportsDTO reports = new ReportsDTO(getReports());
        try {
            String json = objectMapper.writeValueAsString(reports);
            Report report = new Report(json);
            return reportRepository.save(report).getId();
        } catch (JsonProcessingException e) {
            throw new ApiException("Не удалось получить json из объекта");
        }
    }

    @Override
    public byte[] getReportById(int id) {
        String report = reportRepository
                .findById(id)
                .orElseThrow(() -> new ApiException("Отчет не найден"))
                .getReport();
        return report.getBytes();
    }

    @Override
    public int formReportFromDisk() {
        ReportsDTO reports = new ReportsDTO(getReports());
        try {
            String json = objectMapper.writeValueAsString(reports);
            String filePath = generateFilePath();
            writeJsonToFile(json, filePath);
            Report report = new Report(json, filePath);
            return reportRepository.save(report).getId();
        } catch (IOException e) {
            throw new ApiException("Не удалось сформировать файл");
        }
    }
    @Override
    public byte[] getReportFromDiskById(int id) {
        try {
            String path = reportRepository
                    .findById(id)
                    .orElseThrow(() -> new ApiException("Отчет не найден"))
                    .getFilePath();
            Path reportPath = Paths.get(path);
            return Files.readAllBytes(reportPath);
        } catch (IOException e) {
            throw new ApiException("Ошибка при чтении файла отчета");
        }
    }

    private List<ReportDTO> getReports() {
        List<Object[]> reports = reportRepository.getReport();
        return reports.stream()
                .map(r -> new ReportDTO((String) r[0], (Long) r[1], (Integer) r[2], (Integer) r[3], (BigDecimal) r[4]))
                .toList();
    }

    private void writeJsonToFile(String json, String filePath) throws IOException {
        Files.write(Paths.get(filePath), json.getBytes());
    }
    @Value("${reports.file-path}")
    private String directoryPath;
    private String generateFilePath() {

        String fileName = "reportFile-" + UUID.randomUUID().toString() + ".txt";
        return Paths.get(directoryPath, fileName).toString();
    }

}
