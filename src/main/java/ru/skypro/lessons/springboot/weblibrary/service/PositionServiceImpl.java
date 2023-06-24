package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.exception.ApiException;
import ru.skypro.lessons.springboot.weblibrary.repository.PositionRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final EmployeeService employeeService;
    private final PositionRepository positionRepository;
    private static final Logger LOG = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Override
    public List<EmployeeDTO> getEmployeesByPosition(String position) {
        LOG.debug("getEmployeesByPosition метод был вызван с параметром: {}", position);
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
