package ru.skypro.lessons.springboot.weblibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.List;

//@Service("employeeService123") имя (id) бина в контексте (spring контейнер) спринга
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Override
    public double getSumSalary() {
        return employeeRepository.getSumSalary();
    }

    @Override
    public List<Employee> getMinSalary() {
        return employeeRepository.getMinSalary();
    }

    @Override
    public List<Employee> getMaxSalary() {
        return employeeRepository.getMaxSalary();
    }

    @Override
    public List<Employee> getAnAverageSalary() {
        return employeeRepository.getAnAverageSalary();
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    @Override
    public void createEmployees(List<Employee> employees) {
        employeeRepository.createEmployees(employees);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.getEmployee(id);
    }
    @Override
    public void changeEmployeeById(int id, Employee employee) {
        employeeRepository.changeEmployeeById(id, employee);
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public List<Employee> getEmployeesWithSalaryHigherThan(int salary) {
        return employeeRepository.getEmployeesWithSalaryHigherThan(salary);
    }
}
