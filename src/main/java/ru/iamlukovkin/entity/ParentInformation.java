package ru.iamlukovkin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "parent_information", schema = "public")
public class ParentInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_information_id_gen")
    @SequenceGenerator(name = "parent_information_id_gen", sequenceName = "parent_information_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "passport_series", length = 4)
    private String passportSeries;

    @Column(name = "passport_number", length = 6)
    private String passportNumber;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "patronymic", length = 30)
    private String patronymic;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

}