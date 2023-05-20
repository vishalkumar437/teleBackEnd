package com.hms.tele_medicine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "qualifications")
public class Qualification {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String qualificationName;

    private String instituteName;

    private Integer passingYear;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Doctor doctor;
}
