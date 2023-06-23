package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;
import ru.skypro.lessons.springboot.weblibrary.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeService employeeService;

    private final PositionService positionService;

    @Autowired
    public EmployeesController(EmployeeService employeeService, PositionService positionService) {
        this.employeeService = employeeService;
        this.positionService = positionService;
    }

    @PostMapping
    public void createEmployees(@RequestBody List<EmployeeDTO> employees) {
        employeeService.createEmployees(employees);
    }

    @PutMapping("{id}")
    public void changeEmployeeById(@PathVariable("id") int id, @RequestBody EmployeeDTO employee) {
        employeeService.changeEmployeeById(id, employee);
    }

    @GetMapping("{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("{id}")
    public void deleteEmployeeByID(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/salaryHigherThan")
    public List<EmployeeDTO> getEmployeesWithSalaryHigherThan(@RequestParam("salary") int salary) {
        return employeeService.getEmployeesWithSalaryHigherThan(salary);
    }

    @GetMapping("/withHighestSalary")
    public List<EmployeeDTO> getEmployeesWithHighestSalary() {
        return employeeService.getEmployeesWithHighestSalary();
    }

    @GetMapping
    public List<EmployeeDTO> getEmployeesByPosition(@RequestParam(value = "position", required = false) String position) {
        return positionService.getEmployeesByPosition(position);
    }

    @GetMapping("/page")
    public Page<EmployeeDTO> getEmployeesByPosition(Pageable pageable) {
        return employeeService.getPageableEmployees(pageable);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadEmployeesFromFile(@RequestPart("report") MultipartFile report) {
        employeeService.uploadEmployeesFromFile(report);
    }
}
