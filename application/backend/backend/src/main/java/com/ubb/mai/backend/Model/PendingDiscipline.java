package com.ubb.mai.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
public class PendingDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
    private String type;

    private Integer noCredits;

    private Integer semester;

    private Integer year;

    public PendingDiscipline(String name, Teacher teacher, Integer noCredits, Integer semester, Integer year) {
        this.name = name;
        this.teacher = teacher;
        this.noCredits = noCredits;
        this.semester = semester;
        this.year = year;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

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


