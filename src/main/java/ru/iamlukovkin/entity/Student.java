package ru.iamlukovkin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student", schema = "public")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_gen")
    @SequenceGenerator(name = "student_id_gen", sequenceName = "student_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "patronymic", length = 30)
    private String patronymic;

    @Column(name = "snils", length = 13)
    private String snils;

    @Column(name = "inn", length = 12)
    private String inn;

    @Column(name = "certificate_number", length = 14)
    private String certificateNumber;

    @Column(name = "passport_series", length = 4)
    private String passportSeries;

    @Column(name = "passport_number", length = 6)
    private String passportNumber;

    @Column(name = "actual_address", length = 100)
    private String actualAddress;

    @Column(name = "registration_address", nullable = false, length = 100)
    private String registrationAddress;

    @Column(name = "temporary_address", length = 100)
    private String temporaryAddress;

    @Column(name = "math_result")
    private Integer mathResult;

    @Column(name = "russian_language_result")
    private Integer russianLanguageResult;

    @Column(name = "profile_subject_result")
    private Integer profileSubjectResult;

}