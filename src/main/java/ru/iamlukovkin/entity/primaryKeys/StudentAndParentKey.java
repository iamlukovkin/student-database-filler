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
public class StudentAndParentKey implements java.io.Serializable {
    private static final long serialVersionUID = -6740210324297947819L;
    @Column(name = "student", nullable = false)
    private Integer student;

    @Column(name = "parent", nullable = false)
    private Integer parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentAndParentKey entity = (StudentAndParentKey) o;
        return Objects.equals(this.parent, entity.parent) &&
                Objects.equals(this.student, entity.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, student);
    }

}