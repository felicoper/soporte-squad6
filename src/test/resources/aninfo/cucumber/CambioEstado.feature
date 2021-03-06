Feature: Cambio de estado del Ticket

  Scenario: CAMBIO_ESTADO_TICKET_CON_ESTADO_NO_CERRADO
    Given ticket existente con estado "no cerrado"
    When el ingeniero de soporte cambie el ticket al nuevo estado "no cerrado"
    Then el sistema registra el cambio de estado del ticket
    And indica que se cambio el estado exitosamente

  Scenario: CAMBIO_ESTADO_A_CERRADO_TICKET_CON_ESTADO_NO_CERRADO
    Given ticket existente con estado "no cerrado"
    When el ingeniero de soporte cambie el ticket al nuevo estado "cerrado"
    Then el sistema indica que pasara a cerrar el ticket y no se podra volver a cambiar el estado
    And pregunta si desea continuar con el cierre del ticket

  Scenario: CAMBIO_ESTADO_TICKET_CON_ESTADO_CERRADO
    Given ticket existente con estado "cerrado"
    When el ingeniero de soporte intente cambiar el estado del ticket
    Then el sistema indica que no se puede cambiar el estado de un ticket cerrado
