Feature: Visualizar un ticket

    Scenario: OBTENCION_INFORMACION_TICKET
    Given un ticket "existente"
    When el ingeniero de soporte consulta el ticket
    Then el sistema mostrara toda la informacion del ticket

    Scenario: OBTENCION_INFORMACION_TICKET_NO_EXISTENTE
    Given un ticket "inexistente"
    When el ingeniero de soporte consulta el ticket
    Then el sistema indicara que no existe el ticket solicitado