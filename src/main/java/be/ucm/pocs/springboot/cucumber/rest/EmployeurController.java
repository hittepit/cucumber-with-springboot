package be.ucm.pocs.springboot.cucumber.rest;

import java.util.List;

import be.ucm.pocs.springboot.cucumber.application.dto.EmployerDto;
import be.ucm.pocs.springboot.cucumber.application.services.EmployeurService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employeurs")
public class EmployeurController {
    private final EmployeurService employeurService;

    public EmployeurController(EmployeurService employeurService) {
        this.employeurService = employeurService;
    }

    @GetMapping
    public List<EmployerDto> list() {
        return employeurService.findAll();
    }

    @PostMapping("/{numDos}")
    public int create(@PathVariable("numDos") String numeroDossier, @RequestBody String denomination) {
        return employeurService.create(numeroDossier, denomination);
    }
}
