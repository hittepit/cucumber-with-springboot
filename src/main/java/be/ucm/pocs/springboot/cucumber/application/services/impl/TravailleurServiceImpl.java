package be.ucm.pocs.springboot.cucumber.application.services.impl;

import be.ucm.pocs.springboot.cucumber.application.dto.TravailleurDto;
import be.ucm.pocs.springboot.cucumber.application.services.TravailleurService;
import be.ucm.pocs.springboot.cucumber.dao.EmployeurRepository;
import be.ucm.pocs.springboot.cucumber.dao.TravailleurRepository;
import be.ucm.pocs.springboot.cucumber.model.Employeur;
import be.ucm.pocs.springboot.cucumber.process.NumeroTravailleurGenerator;
import be.ucm.pocs.springboot.cucumber.model.Travailleur;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TravailleurServiceImpl implements TravailleurService {
    private final EmployeurRepository employeurRepository;
    private final TravailleurRepository travailleurRepository;

    public TravailleurServiceImpl(EmployeurRepository employeurRepository, TravailleurRepository travailleurRepository) {
        this.employeurRepository = employeurRepository;
        this.travailleurRepository = travailleurRepository;
    }

    @Transactional(readOnly = false)
    @Override
    public int addTravailleur(String numeroDossier, TravailleurDto travailleurDto) {
        Employeur employeur = employeurRepository.findByNumeroDossier(numeroDossier).orElseThrow();
        NumeroTravailleurGenerator numeroTravailleurGenerator = new NumeroTravailleurGenerator();
        String numero = numeroTravailleurGenerator.next(employeur);
        Travailleur travailleur = new Travailleur(numero, travailleurDto.getFirstName(), travailleurDto.getLastName(), employeur);
        travailleurRepository.save(travailleur);
        return travailleur.getId();
    }
}
