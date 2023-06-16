package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
//@RequiredArgsConstructor
public class SalaryController {
    private final EmployeeService employeeService;

    @Autowired // инджект бина через интерфейс, спринг ищет имплементацию интерфейса

    public SalaryController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/sum")
    public double getSumSalary(){
        return employeeService.getSumSalary();
    }
    @GetMapping("/salary/min")
    public List<Employee> getMinSalary(){
        return employeeService.getMinSalary();
    }
    @GetMapping("/salary/max")
    public List<Employee> getMaxSalary(){
        return employeeService.getMaxSalary();
    }
    @GetMapping("/high-salary")
    public List<Employee> getAnAverageSalary(){
        return employeeService.getAnAverageSalary();
    }


}
