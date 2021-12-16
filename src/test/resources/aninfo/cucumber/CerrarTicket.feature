Feature: cerrar un ticket

  Scenario: CIERRE_TICKET_NO_CERRADO
    Given Ticket existente con estado no cerrado
    When El ingeniero de soporte cierre el ticket
    Then El sistema registra que se cerro el ticket, guarda la fecha de cierre y notifica que se cerro exitosamente

  Scenario: CIERRE_TICKET_CERRADO
    Given Ticket existente con estado cerrado
    When El ingeniero de soporte cierre el ticket
    Then El sistema indica que el ticket ya se encuentra cerrado