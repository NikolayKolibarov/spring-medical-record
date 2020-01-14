package nnk.areas.examinations.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "examination_types")
public class ExaminationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relations
    @OneToMany(mappedBy = "type")
    private Set<Examination> examinations;

    // Fields
    @Column(name = "name")
    private String name;


    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}