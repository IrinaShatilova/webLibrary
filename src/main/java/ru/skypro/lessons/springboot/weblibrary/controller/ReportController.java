package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;
import ru.skypro.lessons.springboot.weblibrary.service.PositionService;
import ru.skypro.lessons.springboot.weblibrary.service.ReportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class ReportController {
    private final EmployeeService employeeService;
    private final ReportService reportService;

    @Autowired
    public ReportController(EmployeeService employeeService, PositionService positionService, ReportService reportService) {
        this.employeeService = employeeService;
        this.reportService = reportService;
    }
    @PostMapping(value ="/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadEmployeesFromReport(@RequestPart("report") MultipartFile report) throws IOException {
            List<EmployeeDTO> employeeDTO = reportService.uploadEmployeesFromReport(report);
            employeeService.createEmployees(employeeDTO);
    }

}
