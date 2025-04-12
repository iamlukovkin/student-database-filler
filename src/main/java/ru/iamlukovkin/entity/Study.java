package ru.iamlukovkin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.iamlukovkin.entity.primaryKeys.StudyKey;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "study", schema = "public")
public class Study {
    @SequenceGenerator(name = "study_id_gen", sequenceName = "parent_information_id_seq", allocationSize = 1)
    @EmbeddedId
    private StudyKey id;

    @MapsId("student")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student", nullable = false)
    private Student student;

    @Column(name = "is_full_time")
    private Boolean isFullTime;

    @Column(name = "is_by_agreement")
    private Boolean isByAgreement;

    @Column(name = "is_commercial")
    private Boolean isCommercial;

}