package com.ubb.mai.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Student {

    public Student(String username, String name) {
        this.username = username;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_id")
    private Long group;

    private String username;

    private String name;

    private Integer currentYear;
    private Integer sem1Grade;
    private Integer sem2Grade;
    private Integer year1Grade;
    private Integer sem3Grade;
    private Integer sem4Grade;
    private Integer year2Grade;
    private Integer sem5Grade;
    private Integer sem6Grade;
    private Integer year3Grade;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    Set<CourseRegistration> registrations = new HashSet<>();

}
