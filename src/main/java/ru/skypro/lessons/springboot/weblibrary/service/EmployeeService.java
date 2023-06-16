package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.entity.Employee;

import java.util.List;

public interface EmployeeService {
    double getSumSalary();
    List<Employee> getMinSalary();
    List<Employee> getMaxSalary();
    List<Employee> getAnAverageSalary();
    List<Employee> getEmployees();

     void createEmployees(List<Employee> employees);

    Employee getEmployee(int id);

    void changeEmployeeById(int id, Employee employee);
    void deleteEmployeeById(int id);
    List<Employee> getEmployeesWithSalaryHigherThan(Integer salary);
}