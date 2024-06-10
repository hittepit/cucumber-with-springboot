package be.ucm.pocs.springboot.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeurSteps {
    private String numeroDossier;
    private String denomination;

    @Given("{string} as numero dossier")
    public void asNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    @Given("{string} as denomination")
    public void asDenomination(String denomination) {
        this.denomination = denomination;
    }

    @When("I create an employeur")
    public void iCreateAnEmployeur() {

    }

    @Then("a new employeur is created with {string} as numero dossier and {string} as denomination")
    public void aNewEmployeurIsCreatedWithAsNumeroDossierAndAsDenomination(String numeroDossier, String denomination) {
    }
}
