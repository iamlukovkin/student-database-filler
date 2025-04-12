package ru.iamlukovkin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.iamlukovkin.entity.primaryKeys.StudentAndParentKey;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_and_parent", schema = "public")
public class StudentAndParent {
    @EmbeddedId
    private StudentAndParentKey id;

    @MapsId("student")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student", nullable = false)
    private Student student;

    @MapsId("parent")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parent", nullable = false)
    private ParentInformation parent;

    @Column(name = "who_is", length = 6)
    private String whoIs;

}