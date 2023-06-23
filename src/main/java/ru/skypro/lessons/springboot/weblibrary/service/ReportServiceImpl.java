package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportsDTO;
import ru.skypro.lessons.springboot.weblibrary.exception.ApiException;
import ru.skypro.lessons.springboot.weblibrary.model.Report;
import ru.skypro.lessons.springboot.weblibrary.repository.ReportRepository;

import java.math.BigDecimal;
import java.util.List;


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

    private List<ReportDTO> getReports() {
        List<Object[]> reports = reportRepository.getReport();
        return reports.stream()
                .map(r -> new ReportDTO((String) r[0], (Long) r[1], (Integer) r[2], (Integer) r[3], (BigDecimal) r[4]))
                .toList();
    }
}
