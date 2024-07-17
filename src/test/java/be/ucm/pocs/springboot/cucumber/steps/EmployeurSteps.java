package be.ucm.pocs.springboot.cucumber.steps;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import be.ucm.pocs.springboot.cucumber.dao.EmployeurRepository;
import be.ucm.pocs.springboot.cucumber.model.Employeur;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

public class EmployeurSteps {
    private final MockMvc mockMvc;
    private final EmployeurRepository employeurRepository;

    private String numeroDossier;
    private String denomination;
    private ResultActions resultActions;

    public EmployeurSteps(MockMvc mockMvc, EmployeurRepository employeurRepository) {
        this.mockMvc = mockMvc;
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

    @DataTableType
    public Employeur createEmployeur(Map<String, String> data) {
        return new Employeur(data.get("number"), data.get("denomination"));
    }

    @Given("the following employers")
    public void theFollowingEmployers(List<Employeur> employeurs) {
        employeurRepository.saveAll(employeurs);
    }

    @Given("an employer {string} with {numeroDossier} as fileNumber")
    public void anEmployerWithAsFileNumber(String denomination, String numeroDossier) throws Exception {
        final Employeur employeur = new Employeur(numeroDossier, denomination);
        employeurRepository.save(employeur);
    }

    @When("I create an employeur")
    public void iCreateAnEmployeur() throws Exception {
        mockMvc.perform(post("/api/employeurs/"+numeroDossier).content(denomination))
                .andExpect(status().isOk());
    }

    @When("I list employers")
    public void iListEmployers() throws Exception {
        resultActions = mockMvc.perform(get("/api/employeurs"));
    }

    @Then("a new employeur is created with {string} as numero dossier and {string} as denomination")
    public void aNewEmployeurIsCreatedWithAsNumeroDossierAndAsDenomination(String numeroDossier, String denomination) {
        final List<Employeur> employeurs = employeurRepository.findAll();
        assertEquals(1, employeurs.size());
        assertEquals(numeroDossier, employeurs.getFirst().getNumeroDossier());
        assertEquals(denomination, employeurs.getFirst().getDenomination());
    }

    @Then("^I get ([0-9]*) employers$")
    public void iGetEmployers(int numberOfEmployers) throws Exception {
        resultActions
                .andExpect(jsonPath("$").value(hasSize(numberOfEmployers)))
                .andExpect(jsonPath("$[*].numeroDossier").value(hasItems("000001", "000002","000003")));
    }
}
