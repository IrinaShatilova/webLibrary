package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.exception.ApiException;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final ObjectMapper objectMapper;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createEmployees(List<EmployeeDTO> employees) {
        employeeRepository.saveAll(employees.stream().map(EmployeeDTO::toEmployee).toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .map(EmployeeDTO::fromEmployee)
                .orElseThrow(() -> new ApiException("Сотрудник не найден"));
    }
    @Transactional
    @Override
    public void changeEmployeeById(int id, EmployeeDTO employee) {
        if (employee.getId() != id) {
            throw new ApiException("Некорректный идентификатор");
        }
        getEmployeeById(employee.getId());
        employeeRepository.save(employee.toEmployee());
    }
    @Transactional
    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithSalaryHigherThan(Integer salary) {
        return employeeRepository.getEmployeesWithSalaryHigherThan(salary)
                .stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithHighestSalary() {
        return employeeRepository.getEmployeesWithHighestSalary()
                .stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();

    }

    @Override
    public Page<EmployeeDTO> getPageableEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(EmployeeDTO::fromEmployee);
    }

    @Override
    public EmployeeFullInfo getEmployeeFullInfoById(int id) {
        if (employeeRepository.getEmployeeFullInfoById(id) != null) {
            return employeeRepository.getEmployeeFullInfoById(id);
        }
        throw new ApiException("Сотрудник не найден");
    }

    @Override
    public void uploadEmployeesFromFile(MultipartFile report) {
        String jsonContent;
        try {
            jsonContent = new String(report.getBytes(), StandardCharsets.UTF_8);
            List<EmployeeDTO> employees = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
            createEmployees(employees);
        } catch (IOException e) {
            throw new ApiException("Невозможно прочесть файл");
        }
    }

}
