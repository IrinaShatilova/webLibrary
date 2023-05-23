package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    double getSumSalary();
    List<Employee> getMinSalary();
    List<Employee> getMaxSalary();
    List<Employee> getAnAverageSalary();
}