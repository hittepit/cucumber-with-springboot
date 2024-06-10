package be.ucm.pocs.springboot.cucumber.rest;

import be.ucm.pocs.springboot.cucumber.services.EmployeurService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employeur")
public class EmployeurController {
    private final EmployeurService employeurService;

    public EmployeurController(EmployeurService employeurService) {
        this.employeurService = employeurService;
    }

    @PostMapping("/{numDos}")
    public int create(@PathVariable("numDos") String numeroDossier, @RequestBody String denomination) {
        return employeurService.create(numeroDossier, denomination);
    }
}
