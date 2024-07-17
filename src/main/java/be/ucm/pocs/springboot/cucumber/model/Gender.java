package be.ucm.pocs.springboot.cucumber.model;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public static Gender fromValue(String value) {
        return switch (value) {
            case "M" -> Gender.MALE;
            case "F" -> Gender.FEMALE;
            default -> throw new IllegalArgumentException("Unknown value: " + value);
        };
    }

    public String getValue() {
        return value;
    }
}
