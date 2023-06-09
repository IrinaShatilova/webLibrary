package ru.skypro.lessons.springboot.weblibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String report;

    @Column
    private String filePath;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createAt;

    public Report(int id, String report, String filePath, Instant createAt) {
        this.id = id;
        this.report = report;
        this.filePath = filePath;
        this.createAt = createAt;
    }

    public Report(String report) {
        this.report = report;
    }
    public Report(String report, String filePath) {
        this.filePath = filePath;
        this.report = report;
    }

}
