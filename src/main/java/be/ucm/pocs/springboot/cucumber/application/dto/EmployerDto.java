package be.ucm.pocs.springboot.cucumber.application.dto;

import be.ucm.pocs.springboot.cucumber.model.Employeur;

public class EmployerDto {
    private final Integer id;
    private final String numeroDossier;
    private final String denomination;

    public EmployerDto(Employeur employeur) {
        this.id = employeur.getId();
        this.numeroDossier = employeur.getNumeroDossier();
        this.denomination = employeur.getDenomination();
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
}
