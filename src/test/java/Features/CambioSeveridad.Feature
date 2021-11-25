Feature: Cambio de severidad del Ticket

  Scenario: Cambio de severidad de Ticket con estado No Cerrado
    Given ticket existente con estado no cerrado
    When el ingeniero de soporte cambie la severidad del ticket
    Then el sistema registra el cambio de la severidad del ticket
    And indica que se cambio la severidad exitosamente

  Scenario: Cambio de severidad de Ticket con estado Cerrado
    Given ticket existente con estado no cerrado
    When el ingeniero de soporte cambie la severidad del ticket
    Then el sistema indica que no se puede cambiar la severidad del ticket