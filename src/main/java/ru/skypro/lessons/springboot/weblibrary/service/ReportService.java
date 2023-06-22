package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;

import java.io.IOException;
import java.util.List;

public interface ReportService {
 public List<EmployeeDTO> uploadEmployeesFromReport (MultipartFile report) throws IOException;
}
