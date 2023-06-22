package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {
    private final ObjectMapper objectMapper;
    @Autowired

    public ReportServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<EmployeeDTO> uploadEmployeesFromReport (MultipartFile report) throws IOException {
        String jsonContent = new String(report.getBytes(), StandardCharsets.UTF_8);
        return objectMapper.readValue(jsonContent, new TypeReference<>() {
        });

    }
}