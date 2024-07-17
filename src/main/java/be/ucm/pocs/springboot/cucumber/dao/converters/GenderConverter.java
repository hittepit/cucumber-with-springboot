package be.ucm.pocs.springboot.cucumber.dao.converters;

import be.ucm.pocs.springboot.cucumber.model.Gender;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Gender.fromValue(dbData);
    }
}
