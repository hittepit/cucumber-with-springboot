package be.ucm.pocs.springboot.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import be.ucm.pocs.springboot.cucumber.application.dto.TravailleurDto;
import be.ucm.pocs.springboot.cucumber.dao.EmployeurRepository;
import be.ucm.pocs.springboot.cucumber.dao.TravailleurRepository;
import be.ucm.pocs.springboot.cucumber.model.Employeur;
import be.ucm.pocs.springboot.cucumber.model.Gender;
import be.ucm.pocs.springboot.cucumber.model.Travailleur;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

public class TravailleurSteps implements En {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final EmployeurRepository employeurRepository;
    private final TravailleurRepository travailleurRepository;
    private Travailleur travailleur;

    public TravailleurSteps(MockMvc mockMvc, ObjectMapper objectMapper, EmployeurRepository employeurRepository, TravailleurRepository travailleurRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.employeurRepository = employeurRepository;
        this.travailleurRepository = travailleurRepository;

        When("I add to employer {numeroDossier} a {gender} worker {string} {string}", (String numeroDossier, Gender gender, String prenom, String nom) -> {
            TravailleurDto dto = new TravailleurDto();
            dto.setFirstName(prenom);
            dto.setLastName(nom);
            dto.setGender(gender.getValue());
            mockMvc.perform(post("/api/employeurs/" + numeroDossier + "/travailleurs")
                            .content(objectMapper.writeValueAsString(dto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        });

        Given("^employer ([0-9]{6}) has employees$", (String numDossier, DataTable data) -> {
            final Employeur employeur = employeurRepository.findByNumeroDossier(numDossier).orElseThrow();
            data.asMaps()
                    .forEach(els -> {
                        final Travailleur travailleur = new Travailleur(els.get("number"),
                                els.get("firstname"),
                                els.get("lastname"),
                                Gender.fromValue(els.get("gender").substring(0,1).toUpperCase()),
                                employeur);
                        travailleurRepository.save(travailleur);
                    });
        });

        Then("a {gender} worker {string} {string} is added to employer {numeroDossier}",
                (Gender gender, String prenom, String nom, String numeroDossier) -> {
                    final Employeur employeur = employeurRepository.findByNumeroDossier(numeroDossier).orElseThrow();
                    travailleur = employeur.getTravailleurs().stream()
                            .filter(t -> t.getFirstName().equals(prenom) && t.getLastName().equals(nom) && t.getGender() == gender)
                            .findFirst().orElseThrow();
                });

        And("^the worker has a workerNumber$", () -> {
            assertNotNull(travailleur.getNumeroTravailleur());
        });

        And("^the worker has ([0-9]{6}) as workerNumber$", (String numTravailleur) -> {
            assertEquals(numTravailleur, travailleur.getNumeroTravailleur());
        });
    }
}
