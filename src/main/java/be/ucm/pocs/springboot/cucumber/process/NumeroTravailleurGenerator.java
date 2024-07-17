package be.ucm.pocs.springboot.cucumber.process;

import java.util.Optional;

import be.ucm.pocs.springboot.cucumber.model.Employeur;
import be.ucm.pocs.springboot.cucumber.model.Travailleur;

public class NumeroTravailleurGenerator {
    public String next(Employeur employeur) {
        final Optional<String> first = employeur.getTravailleurs().stream()
                .map(Travailleur::getNumeroTravailleur)
                .max(String::compareTo);

        final String lastNumber = first.orElse("000000");

        final int nextNumber = Integer.parseInt(lastNumber)+1;

        return String.format("%06d", nextNumber);
    }
}
