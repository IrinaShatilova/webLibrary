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

import java.util.List;

@RestController
@RequestMapping("/employees")
public class ReportController {
    private final EmployeeService employeeService;

    private final PositionService positionService;
    private final ReportService reportService;

    @Autowired
    public ReportController(EmployeeService employeeService, PositionService positionService, ReportService reportService) {
        this.employeeService = employeeService;
        this.positionService = positionService;
        this.reportService = reportService;
    }
    @PostMapping(value ="/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadEmployeesFromFile(@RequestPart("file") MultipartFile file) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (!"json".equals(extension)) {
            throw ;
        }

    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void loadEmployeesFromFileAndSave(@RequestParam("file") MultipartFile file) throws IOException {
        List<EmployeeDTO> employeeDTO = employeeService.loadEmployeesFromFile(file);
        employeeService.addEmployee(employeeDTO);
    }

    @PostMapping("/report")
    public String getReportByDepartment() {
        Long reportId = employeeService.getReportByDepartment();
        return "Идентификатор (id) сохраненного отчёта: " + reportId;
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<ByteArrayResource> getReportByIdAndDownload(@PathVariable Long id) {
        employeeService.generateJsonFileFromReport(id);
        return employeeService.getReportResponseById(id);
    }











}
