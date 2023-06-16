package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void createEmployees(@RequestBody List<Employee> employees){
        employeeService.createEmployees(employees);
    }

    @PutMapping("{id}")
    public void changeEmployeeById(@PathVariable("id") int id, @RequestBody Employee employee){
        employeeService.changeEmployeeById(id, employee);
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable("id") int id){
        return employeeService.getEmployee(id);
    }
    @DeleteMapping("{id}")
    public void deleteEmployeeByID(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
    }
    @GetMapping("/salaryHigherThan")
    public List<Employee> getEmployeesWithSalaryHigherThan(@RequestParam("salary") int salary) {
        return employeeService.getEmployeesWithSalaryHigherThan(salary);
    }
}
