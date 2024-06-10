package be.ucm.pocs.springboot.cucumber.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "divers", name = "travailleur")
public class Travailleur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "num_trav", updatable = false, nullable = false)
    private String numeroTravailleur;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "employeur_fk", nullable = false, updatable = false)
    private Employeur employeur;

    protected Travailleur() {
        //JPA
    }

    public Travailleur(String numeroTravailleur, String firstName, String lastName, Employeur employeur) {
        //TODO vérification que rien n'est null

        this.numeroTravailleur = numeroTravailleur;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeur = employeur;

        if(this.employeur.add(this)){
            throw new IllegalArgumentException("Travailleur avec le même numéro existe déjà: "+numeroTravailleur);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getNumeroTravailleur() {
        return numeroTravailleur;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Employeur getEmployeur() {
        return employeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Travailleur that)) return false;
        return Objects.equals(numeroTravailleur, that.numeroTravailleur) && Objects.equals(employeur, that.employeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroTravailleur, employeur);
    }
}
