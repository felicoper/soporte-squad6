Feature: cerrar un ticket

  Scenario: CIERRE_TICKET_NO_CERRADO
    Given ticket existente con estado "no cerrado"
    When el ingeniero de soporte cierre el ticket
    Then el sistema registra que se cerro el ticket y notifica que se cerro exitosamente

  Scenario: CIERRE_TICKET_CERRADO
    Given ticket existente con estado "cerrado"
    When el ingeniero de soporte cierre el ticket
    Then el sistema indica que el ticket ya se encuentra cerrado