package be.ucm.pocs.springboot.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import be.ucm.pocs.springboot.cucumber.application.dto.TravailleurDto;
import be.ucm.pocs.springboot.cucumber.dao.EmployeurRepository;
import be.ucm.pocs.springboot.cucumber.model.Employeur;
import be.ucm.pocs.springboot.cucumber.model.Travailleur;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.When;
import io.cucumber.java8.En;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TravailleurSteps implements En {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final EmployeurRepository employeurRepository;
    private Travailleur travailleur;

    public TravailleurSteps(MockMvc mockMvc, ObjectMapper objectMapper, EmployeurRepository employeurRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.employeurRepository = employeurRepository;

        When("I add to employer {string} a worker {string} {string}", (String numeroDossier, String prenom, String nom) -> {
            TravailleurDto dto = new TravailleurDto();
            dto.setFirstName(prenom);
            dto.setLastName(nom);
            mockMvc.perform(post("/api/employeurs/" + numeroDossier + "/travailleurs")
                            .content(objectMapper.writeValueAsString(dto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        });

        Then("worker {string} {string} is added to employer {string}",
                (String prenom, String nom, String numeroDossier) -> {
                    final Employeur employeur = employeurRepository.findByNumeroDossier(numeroDossier).orElseThrow();
                    travailleur = employeur.getTravailleurs().stream()
                            .filter(t -> t.getFirstName().equals(prenom) && t.getLastName().equals(nom))
                            .findFirst().orElseThrow();
                });

        And("^the worker has a workerNumber$", () -> {
            assertNotNull(travailleur.getNumeroTravailleur());
        });
    }
}
