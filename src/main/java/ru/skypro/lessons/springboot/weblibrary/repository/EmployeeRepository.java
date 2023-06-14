package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {
    double getSumSalary();
    List<Employee> getMinSalary();
    List<Employee> getMaxSalary();
    List<Employee> getAnAverageSalary();

    List<Employee> getEmployees();

    void createEmployees(List<Employee> employees);

    Employee getEmployee(int id);

    void changeEmployeeById(int id, Employee employee);
    void deleteEmployeeById(int id);
    List<Employee> getEmployeesWithSalaryHigherThan(int salary);

}
