package ru.iamlukovkin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.iamlukovkin.entity.primaryKeys.StudentEmailKey;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_email", schema = "public")
public class StudentEmail {
    @EmbeddedId
    private StudentEmailKey id;

    @MapsId("student")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student", nullable = false)
    private Student student;

    @Column(name = "email", length = 100)
    private String email;

}