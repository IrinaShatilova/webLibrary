package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final JsonUtil jsonUtil;
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        LOG.debug("getAllEmployees метод был вызван:");
        return employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createEmployees(List<EmployeeDTO> employees) {
        LOG.debug("createEmployees метод был вызван с параметром: {}", jsonUtil.toJson(employees));
        employeeRepository.saveAll(employees.stream().map(EmployeeDTO::toEmployee).toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        LOG.debug("getEmployeeById метод был вызван с параметром: {}", id);
        return employeeRepository.findById(id)
                .map(EmployeeDTO::fromEmployee)
                .orElseThrow(() -> new ApiException("Сотрудник не найден"));
    }
    @Transactional
    @Override
    public void changeEmployeeById(int id, EmployeeDTO employee) {
        LOG.debug("changeEmployeeById метод был вызван с параметрами: {},{}", id, jsonUtil.toJson(employee));
        if (employee.getId() != id) {
            throw new ApiException("Некорректный идентификатор");
        }
        getEmployeeById(employee.getId());
        employeeRepository.save(employee.toEmployee());
    }
    @Transactional
    @Override
    public void deleteEmployeeById(int id) {
        LOG.debug("deleteEmployeeById метод был вызван с параметром: {}", id);
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithSalaryHigherThan(Integer salary) {
        LOG.debug("getEmployeesWithSalaryHigherThan метод был вызван с параметром: {}", salary);
        return employeeRepository.getEmployeesWithSalaryHigherThan(salary)
                .stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithHighestSalary() {
        LOG.debug("getEmployeesWithHighestSalary метод был вызван:");
        return employeeRepository.getEmployeesWithHighestSalary()
                .stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();
    }

    @Override
    public Page<EmployeeDTO> getPageableEmployees(Pageable pageable) {
        LOG.debug("getEmployeesWithSalaryHigherThan метод был вызван с параметром: {}", pageable);
        return employeeRepository.findAll(pageable).map(EmployeeDTO::fromEmployee);
    }

    @Override
    public EmployeeFullInfo getEmployeeFullInfoById(int id) {
        LOG.debug("getEmployeeFullInfoById метод был вызван с параметром: {}", id);
        if (employeeRepository.getEmployeeFullInfoById(id) != null) {
            return employeeRepository.getEmployeeFullInfoById(id);
        }
        throw new ApiException("Сотрудник не найден");
    }

    @Override
    public void uploadEmployeesFromFile(MultipartFile report) {
        LOG.debug("uploadEmployeesFromFile метод был вызван с параметром: {}", jsonUtil.toJson(report));
        String jsonContent;
        try {
            jsonContent = new String(report.getBytes(), StandardCharsets.UTF_8);
            List<EmployeeDTO> employees = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
            createEmployees(employees);
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
            throw new ApiException("Невозможно прочесть файл");
        }
    }
}
