Feature: Cambio de información del problema (título o descripción)

    Scenario: CAMBIO_TITULO_CON_TICKET_NO_CERRADO
    Given un ticket con un estado "no cerrado"
    When el ingeniero de soporte cambia "el titulo" del ticket
    Then el sistema registra el cambio "del titulo" del ticket

    Scenario: CAMBIO_DESCRIPCION_CON_TICKET_NO_CERRADO
    Given un ticket con un estado "no cerrado"
    When el ingeniero de soporte cambia "la descripcion" del ticket
    Then el sistema registra el cambio "de la descripcion" del ticket

    Scenario: CAMBIO_TITULO_CON_TICKET_CERRADO
    Given un ticket con un estado "cerrado"
    When el ingeniero de soporte cambia "el titulo" del ticket
    Then el sistema indicara que no se puede cambiar "el titulo" de un ticket cerrado

    Scenario: CAMBIO_DESCRIPCION_CON_TICKET_CERRADO
    Given un ticket con un estado "cerrado"
    When el ingeniero de soporte cambia "la descripcion" del ticket
    Then el sistema indicara que no se puede cambiar "la descripcion" de un ticket cerrado

    Scenario: CAMBIO_TITULO_CAMPO_VACIO
    Given un ticket con un estado "no cerrado"
    When el ingeniero de soporte cambia "el titulo" del ticket y lo deja vacio
    Then el sistema indicara que no se puede cambiar "el titulo" del ticket si el campo esta vacio

    Scenario: CAMBIO_DESCRIPCION_CAMPO_VACIO
    Given un ticket con un estado "no cerrado"
    When el ingeniero de soporte cambia "la descripcion" del ticket y lo deja vacio
    Then el sistema indicara que no se puede cambiar "la descripcion" del ticket si el campo esta vacio