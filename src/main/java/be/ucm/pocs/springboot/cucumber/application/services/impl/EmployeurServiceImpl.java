package be.ucm.pocs.springboot.cucumber.application.services.impl;

import java.util.List;

import be.ucm.pocs.springboot.cucumber.application.dto.EmployerDto;
import be.ucm.pocs.springboot.cucumber.application.services.EmployeurService;
import be.ucm.pocs.springboot.cucumber.dao.EmployeurRepository;
import be.ucm.pocs.springboot.cucumber.model.Employeur;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeurServiceImpl implements EmployeurService {
    private final EmployeurRepository employeurRepository;

    public EmployeurServiceImpl(EmployeurRepository employeurRepository) {
        this.employeurRepository = employeurRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public int create(String numeroDossier, String denomination) {
        final Employeur employeur = new Employeur(numeroDossier, denomination);
        employeurRepository.save(employeur);
        return employeur.getId();
    }

    @Override
    public List<EmployerDto> findAll() {
        return employeurRepository.findAll().stream().map(EmployerDto::new).toList();
    }
}
