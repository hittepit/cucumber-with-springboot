@transactional
Feature: Employeurs
  Scenario: Creating an employeur
    Given "11111" as numero dossier
    Given "test" as denomination
    When I create an employeur
    Then a new employeur is created with "11111" as numero dossier and "test" as denomination

  Scenario: Listing employers
    Given the following employers
    | number | denomination |
    | 000001 | test 1       |
    | 000002 | test 2       |
    | 000003 | test 3       |
#    Given 3 saved employers
    When I list employers
    Then I get 3 employers
