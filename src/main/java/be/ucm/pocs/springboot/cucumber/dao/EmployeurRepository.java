package be.ucm.pocs.springboot.cucumber.dao;

import be.ucm.pocs.springboot.cucumber.model.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeurRepository extends JpaRepository<Employeur, Integer> {
}
