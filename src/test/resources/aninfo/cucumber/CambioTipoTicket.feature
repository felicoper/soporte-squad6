Feature: Cambio de tipo de ticket

    Scenario: CAMBIO_TIPO_TICKET_CON_TICKET_NO_CERRADO
    Given ticket con estado "no cerrado"
    When el ingeniero de soporte cambie el tipo del ticket
    Then el sistema registra el cambio de tipo del ticket

    Scenario: CAMBIO_TIPO_TICKET_CON_TICKET_CERRADO
    Given ticket con estado "cerrado"
    When el ingeniero de soporte cambie el tipo del ticket
    Then el sistema indica que no se puede cambiar el tipo de un ticket cerrado

