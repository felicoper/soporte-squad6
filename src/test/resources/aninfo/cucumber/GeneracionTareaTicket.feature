Feature: Generación tarea al ticket

    Scenario: TICKET_NO_CERRADO_Y_TAREA_VALIDA
    Given un ticket no cerrado y una tarea con su identificador "correcto"
    When el ingeniero de soporte asigne una tarea a un ticket
    Then el sistema registrara correctamente la tarea al ticket

    Scenario: TICKET_NO_CERRADO_Y_TAREA_INVALIDA
    Given un ticket no cerrado y una tarea con su identificador "nulo"
    When el ingeniero de soporte asigne una tarea a un ticket
    Then el sistema no registrara correctamente la tarea al ticket indicando que no se puede asignar una tarea con identificador nulo
