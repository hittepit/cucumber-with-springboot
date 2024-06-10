package be.ucm.pocs.springboot.cucumber.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "divers", name = "employeur")
public class Employeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="num_dos", updatable = false, nullable = false)
    private String numeroDossier;
    @Column(name = "denomination", nullable = false)
    private String denomination;
    @OneToMany(mappedBy = "employeur", orphanRemoval = true)
    private Set<Travailleur> travailleurs;

    protected Employeur() {
        //JPA
    }

    public Employeur(String numeroDossier, String denomination) {
        //TODO validations

        this.numeroDossier = numeroDossier;
        this.denomination = denomination;
    }

    public Integer getId() {
        return id;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Set<Travailleur> getTravailleurs() {
        return Collections.unmodifiableSet(travailleurs);
    }

    boolean add(Travailleur travailleur) {
        if(travailleurs == null) {
            travailleurs = new HashSet<>();
        }
        return travailleurs.add(travailleur);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employeur employeur)) return false;
        return Objects.equals(numeroDossier, employeur.numeroDossier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDossier);
    }
}
