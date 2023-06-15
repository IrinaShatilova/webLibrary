package ru.skypro.lessons.springboot.weblibrary.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Employee {
    private int id;
    private String name;
    private int salary;
    private static int idGenerator = 1;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
        id = idGenerator++;
    }

}
