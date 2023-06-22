package ru.skypro.lessons.springboot.weblibrary.dto;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode

public class ReportDTO {
    private String position;
    private int count;
    private double maxSalary;
    private double minSalary;
    private double averageSalary;

    public ReportDTO(String position, int count, double maxSalary, double minSalary, double averageSalary) {
        this.position = position;
        this.count = count;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.averageSalary = averageSalary;
    }
}
