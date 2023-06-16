package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    void createEmployees(List<EmployeeDTO> employees);

    EmployeeDTO getEmployeeById(int id);

    void changeEmployeeById(int id, EmployeeDTO employee);
    void deleteEmployeeById(int id);
    List<EmployeeDTO> getEmployeesWithSalaryHigherThan(Integer salary);
    List<EmployeeDTO> getEmployeesWithHighestSalary();
    Page<EmployeeDTO> getPageableEmployees(Pageable pageable);

    EmployeeFullInfo getEmployeeFullInfoById(int id);


}