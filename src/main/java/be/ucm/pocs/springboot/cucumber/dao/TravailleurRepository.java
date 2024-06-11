package be.ucm.pocs.springboot.cucumber.dao;

import be.ucm.pocs.springboot.cucumber.model.Travailleur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravailleurRepository extends JpaRepository<Travailleur, Integer> {
}
