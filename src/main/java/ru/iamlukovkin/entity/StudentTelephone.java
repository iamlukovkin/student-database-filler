package ru.iamlukovkin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.iamlukovkin.entity.primaryKeys.StudentTelephoneKey;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_telephone", schema = "public")
public class StudentTelephone {
    @EmbeddedId
    private StudentTelephoneKey id;

    @MapsId("student")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student", nullable = false)
    private Student student;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

}