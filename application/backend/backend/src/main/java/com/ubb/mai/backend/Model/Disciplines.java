package com.ubb.mai.backend.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Disciplines {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
    private String type;

    private Integer noCredits;

    private Integer semester;

    private Integer noStudents;

    private Integer year;

    public Disciplines(String name, String type, Teacher teacher, Integer noCredits, Integer semester, Integer noStudents, Integer year) {
        this.name = name;
        this.type = type;
        this.teacher = teacher;
        this.noCredits = noCredits;
        this.semester = semester;
        this.noStudents = noStudents;
        this.year = year;
    }

    public Disciplines(Long id, String name, String type, Integer noCredits, Integer year, Integer semester, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.noCredits = noCredits;
        this.year = year;
        this.semester = semester;
        this.teacher = teacher;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    Set<CourseRegistration> registrations = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Disciplines that = (Disciplines) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
