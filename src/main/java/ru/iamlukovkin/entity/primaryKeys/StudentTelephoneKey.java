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
public class StudentTelephoneKey implements java.io.Serializable {
    private static final long serialVersionUID = 6644034815785717220L;
    @Column(name = "student", nullable = false)
    private Integer student;

    @Column(name = "row_number", nullable = false)
    private Integer rowNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentTelephoneKey entity = (StudentTelephoneKey) o;
        return Objects.equals(this.student, entity.student) &&
                Objects.equals(this.rowNumber, entity.rowNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, rowNumber);
    }

}