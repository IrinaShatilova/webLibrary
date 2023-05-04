package ru.skypro.lessons.springboot.weblibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/salary/sum") //    localhost:8080/employee/salary/sum
    public double getSumSalary(){
        return employeeService.getSumSalary();
    }
    @GetMapping("/salary/min") //    localhost:8080/employee/salary/min
    public List<Employee> getMinSalary(){
        return employeeService.getMinSalary();
    }
    @GetMapping("/salary/max") //    localhost:8080/employee/salary/max
    public List<Employee> getMaxSalary(){
        return employeeService.getMaxSalary();
    }
    @GetMapping("/high-salary") //    localhost:8080/employee/high-salary
    public List<Employee> getAnAverageSalary(){
        return employeeService.getAnAverageSalary();
    }


}
