package ru.skypro.lessons.springboot.weblibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ReportDTO {

    @JsonProperty("position")
    private String position;

    @JsonProperty("count")
    private Long count;

    @JsonProperty("maxSalary")
    private Integer maxSalary;

    @JsonProperty("minSalary")
    private Integer minSalary;

    @JsonProperty("averageSalary")
    private BigDecimal averageSalary;

}
