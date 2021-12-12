Feature: Cambio de severidad del Ticket

  Scenario: CAMBIO_SEVERIDAD_CON_TICKET_NO_CERRADO
    Given ticket existente con estado no cerrado
    When El ingeniero de soporte cambie la severidad del ticket
    Then El sistema registra el cambio de la severidad del ticket

  Scenario: CAMBIO_SEVERIDAD_CON_TICKET_CERRADO
    Given ticket existente con estado cerrado
    When El ingeniero de soporte cambie la severidad del ticket
    Then el sistema indica que no se puede cambiar la severidad del ticket