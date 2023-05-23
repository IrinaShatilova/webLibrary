package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {
    double getSumSalary();
    List<Employee> getMinSalary();
    List<Employee> getMaxSalary();
    List<Employee> getAnAverageSalary();

}
