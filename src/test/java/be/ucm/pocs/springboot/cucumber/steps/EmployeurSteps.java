package be.ucm.pocs.springboot.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import be.ucm.pocs.springboot.cucumber.dao.EmployeurRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class EmployeurSteps {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final EmployeurRepository employeurRepository;

    private String numeroDossier;
    private String denomination;

    public EmployeurSteps(MockMvc mockMvc, ObjectMapper objectMapper, EmployeurRepository employeurRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.employeurRepository = employeurRepository;
    }

    @Given("{string} as numero dossier")
    public void asNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    @Given("{string} as denomination")
    public void asDenomination(String denomination) {
        this.denomination = denomination;
    }

    @When("I create an employeur")
    public void iCreateAnEmployeur() throws Exception {
        Assertions.assertNotNull(mockMvc);
        mockMvc.perform(post("/api/employeur/"+numeroDossier).content(objectMapper.writeValueAsString(denomination)))
                .andExpect(status().isOk());
    }

    @Then("a new employeur is created with {string} as numero dossier and {string} as denomination")
    public void aNewEmployeurIsCreatedWithAsNumeroDossierAndAsDenomination(String numeroDossier, String denomination) {
        assertEquals(1,employeurRepository.findAll().size());
    }
}
