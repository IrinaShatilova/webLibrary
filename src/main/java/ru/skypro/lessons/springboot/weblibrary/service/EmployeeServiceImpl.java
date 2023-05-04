package ru.skypro.lessons.springboot.weblibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.List;

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
}
