package be.ucm.pocs.springboot.cucumber.steps.types;

import be.ucm.pocs.springboot.cucumber.model.Gender;
import io.cucumber.java.ParameterType;

public class TypesDefinitions {
    @ParameterType("[0-9]{6}")
    public String numeroDossier(String numeroDossier) {
        return numeroDossier;
    }

    @ParameterType("male|female")
    public Gender gender(String gender) {
        return Gender.fromValue(gender.substring(0,1).toUpperCase());
    }
}
