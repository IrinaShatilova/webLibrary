package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.exception.ApiException;
import ru.skypro.lessons.springboot.weblibrary.repository.PositionRepository;

import java.util.List;
import java.util.Objects;

@Service
public class PositionServiceImpl implements PositionService {

    private final EmployeeService employeeService;

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(EmployeeService employeeService, PositionRepository positionRepository) {
        this.employeeService = employeeService;
        this.positionRepository = positionRepository;
    }

    @Override
    public List<EmployeeDTO> getEmployeesByPosition(String position) {
        if (Objects.isNull(position)) {
            return employeeService.getAllEmployees();
        }
        return positionRepository.getEmployeesByPosition(position)
                .orElseThrow(() -> new ApiException("Такой позиции нет в таблице positions"))
                .getEmployees()
                .stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();
    }
}
