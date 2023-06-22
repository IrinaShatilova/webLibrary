package ru.skypro.lessons.springboot.weblibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.Instant;
@Data
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column(columnDefinition = "oid")
    private String file;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant createAt;

    public Report(int id, String file, Instant createAt) {
        this.id = id;
        this.file = file;
        this.createAt = createAt;
    }

    public Report(String file) {
        this.file = file;

    }

}
