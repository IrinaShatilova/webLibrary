package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.exception.ApiException;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final List<Employee> employeeList = new ArrayList<>();

    public EmployeeRepositoryImpl() {

    }

    @Override
    public double getSumSalary() {
        double sumSalary = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        return sumSalary;
    }

    @Override
    public List<Employee> getMinSalary() {
        Optional<Employee> minSalary = employeeList.stream()
                .min(Comparator.comparing(Employee::getSalary));
        return minSalary.map(employee -> employeeList.stream()
                        .filter(emp -> emp.getSalary() == employee.getSalary())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<Employee> getMaxSalary() {
        OptionalDouble maxSalary = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .max();
        List<Employee> employeesMax = employeeList.stream()
                .filter(emp -> emp.getSalary() == maxSalary.getAsDouble())
                .collect(Collectors.toList());
        return employeesMax;
    }

    @Override
    public List<Employee> getAnAverageSalary() {
        double averageSalary = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElseThrow(() -> new RuntimeException("Сотрудники не найдены"));
        return employeeList.stream()
                .filter(emp -> emp.getSalary() > averageSalary)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeList;
    }

    @Override
    public void createEmployees(List<Employee> employees) {
        if (nonNull(employees))
            employeeList.addAll(employees);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ApiException("Сотрудник не найден"));
    }

    @Override
    public void changeEmployeeById(int id, Employee employee) {
        Employee findEmployee = getEmployee(id);
        if (nonNull(findEmployee)) {
            findEmployee.setName(employee.getName());
            findEmployee.setSalary(employee.getSalary());
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        Employee employeeDelByID = employeeList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ApiException("Сотрудник не найден"));
        employeeList.remove(employeeDelByID);
    }

    @Override
    public List<Employee> getEmployeesWithSalaryHigherThan(Integer salary) {
        return employeeList.stream()
                .filter(employee -> employee.getSalary() > salary)
                .collect(Collectors.toList());
    }
}
