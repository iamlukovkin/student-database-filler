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
public class StudyKey implements java.io.Serializable {
    private static final long serialVersionUID = -3301655015592128486L;
    @Column(name = "student", nullable = false)
    private Integer student;

    @Column(name = "group_number", nullable = false, length = 5)
    private String groupNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudyKey entity = (StudyKey) o;
        return Objects.equals(this.student, entity.student) &&
                Objects.equals(this.groupNumber, entity.groupNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, groupNumber);
    }

}