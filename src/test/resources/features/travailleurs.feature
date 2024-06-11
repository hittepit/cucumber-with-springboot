Feature: gestion des travailleurs
  Scenario: ajouter un travailleur à un employeur
    Given an employer "lambda" with "000001" as fileNumber
    When I add to employer "000001" a worker "fn" "ln"
    Then worker "fn" "ln" is added to employer "000001"
    And the worker has a workerNumber
