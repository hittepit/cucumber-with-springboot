Feature: Employeurs
  Scenario: Creating an employeur
    Given "11111" as numero dossier
    Given "test" as denomination
    When I create an employeur
    Then a new employeur is created with "11111" as numero dossier and "test" as denomination
