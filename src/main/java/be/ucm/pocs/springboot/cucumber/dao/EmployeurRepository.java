package be.ucm.pocs.springboot.cucumber.dao;

import java.util.Optional;

import be.ucm.pocs.springboot.cucumber.model.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeurRepository extends JpaRepository<Employeur, Integer> {
    Optional<Employeur> findByNumeroDossier(String numeroDossier);
}
