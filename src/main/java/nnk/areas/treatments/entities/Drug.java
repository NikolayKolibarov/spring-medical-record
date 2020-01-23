package nnk.areas.treatments.entities;

import nnk.areas.treatments.entities.Treatment;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "drugs")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relations
    @ManyToMany(mappedBy = "drugs")
    private Set<Treatment> treatments;

    // Fields
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Treatment> getTreatments() {
        return this.treatments;
    }
}