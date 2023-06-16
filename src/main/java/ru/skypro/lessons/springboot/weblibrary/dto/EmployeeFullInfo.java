package ru.skypro.lessons.springboot.weblibrary.dto;

import lombok.Data;

@Data
public class EmployeeFullInfo {
    private String name;
    private Integer salary;
    private String position;


    public EmployeeFullInfo(String name, Integer salary, String position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
    }
}
