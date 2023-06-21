package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;

import java.util.List;

public interface PositionService {
    List<EmployeeDTO> getEmployeesByPosition(String position);
}
