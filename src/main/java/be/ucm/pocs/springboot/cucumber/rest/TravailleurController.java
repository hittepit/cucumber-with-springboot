package be.ucm.pocs.springboot.cucumber.rest;

import be.ucm.pocs.springboot.cucumber.application.dto.TravailleurDto;
import be.ucm.pocs.springboot.cucumber.application.services.TravailleurService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/employeurs/{numDossier}/travailleurs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TravailleurController {
    private final TravailleurService travailleurService;

    public TravailleurController(TravailleurService travailleurService) {
        this.travailleurService = travailleurService;
    }

    @PostMapping
    public int createTravailleur(@PathVariable("numDossier") String numeroDossier, @RequestBody TravailleurDto travailleurDto) {
        return travailleurService.addTravailleur(numeroDossier, travailleurDto);
    }
}
