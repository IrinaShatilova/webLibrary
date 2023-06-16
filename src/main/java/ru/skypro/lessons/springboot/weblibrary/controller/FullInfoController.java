package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;
import ru.skypro.lessons.springboot.weblibrary.service.PositionService;

@RestController
@RequestMapping("/employees")
public class FullInfoController {

    private final EmployeeService employeeService;

    private final PositionService positionService;

    @Autowired
    public FullInfoController(EmployeeService employeeService, PositionService positionService) {
        this.employeeService = employeeService;
        this.positionService = positionService;
    }

    @GetMapping("/{id}/fullInfo")
    public EmployeeFullInfo getEmployeeFullInfoById(@PathVariable int id){
        return employeeService.getEmployeeFullInfoById(id);
    }

}
