package be.ucm.pocs.springboot.cucumber.application.services;

import be.ucm.pocs.springboot.cucumber.application.dto.TravailleurDto;

public interface TravailleurService {
    int addTravailleur(String numeroDossier, TravailleurDto travailleurDto);
}
