package com.hms.tele_medicine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer practicingFrom;

    @Column(unique = true)
    private String phoneNo;

    @Email
    @Column(unique = true)
    private String email;

    private String password;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Specialization> specialisations;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Qualification> qualifications;
}
