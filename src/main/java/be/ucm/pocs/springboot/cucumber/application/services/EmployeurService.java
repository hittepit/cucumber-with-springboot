package be.ucm.pocs.springboot.cucumber.application.services;

import java.util.List;

import be.ucm.pocs.springboot.cucumber.application.dto.EmployerDto;

public interface EmployeurService {
    int create(String numeroDossier, String denomination);

    List<EmployerDto> findAll();
}
