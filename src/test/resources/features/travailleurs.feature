Feature: gestion des travailleurs

  Background:
    Given an employer "lambda" with 000001 as fileNumber

  @transactional
  Scenario: ajouter un travailleur à un employeur
    When I add to employer 000001 a male worker "fn" "ln"
    Then a male worker "fn" "ln" is added to employer 000001
    And the worker has a workerNumber

  @transactional
  Scenario: ajouter un travailleur à un employeur avec des travailleurs
    Given employer 000001 has employees
      | number | firstname | lastname | gender |
      | 000001 | fn1       | ln1      | male   |
      | 000002 | fn2       | ln3      | female |
    When I add to employer 000001 a female worker "fn" "ln"
    Then a female worker "fn" "ln" is added to employer 000001
    And the worker has 000003 as workerNumber
