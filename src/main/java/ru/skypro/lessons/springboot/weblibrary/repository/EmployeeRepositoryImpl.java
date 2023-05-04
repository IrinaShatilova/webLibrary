package ru.skypro.lessons.springboot.weblibrary.repository;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.*;
import java.util.stream.Collectors;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    private final List<Employee> employeeList = List.of(
            new Employee("Катя", 105_000),
            new Employee("Дима", 100_000),
            new Employee("Олег", 50_000),
            new Employee("Вика", 150_000));

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
}
