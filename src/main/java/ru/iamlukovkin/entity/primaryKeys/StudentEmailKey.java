package ru.iamlukovkin.entity.primaryKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class StudentEmailKey implements java.io.Serializable {

    private static final long serialVersionUID = -6561779620810899289L;
    @Column(name = "student", nullable = false)
    private Integer student;

    @Column(name = "row_number", nullable = false)
    private Integer rowNumber;

}