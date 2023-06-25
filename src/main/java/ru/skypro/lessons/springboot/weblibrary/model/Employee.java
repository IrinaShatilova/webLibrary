package ru.skypro.lessons.springboot.weblibrary.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", nullable = false, length = 16)
    private String name;
    @Column(name = "salary")
    private Integer salary;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

}
