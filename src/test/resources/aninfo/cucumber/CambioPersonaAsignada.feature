Feature: Cambio de persona asignadas

    Scenario: PERSONA_ASIGNADA_TICKET_NO_CERRADO
    Given un ticket existente no cerrado y los datos de una persona "existente" en el sistema
    When el ingeniero de soporte cambie la persona asignada
    Then el sistema registrara el cambio de la persona asignada en el ticket e indicara que se cambio exitosamente

    Scenario: PERSONA_ASIGNADA_TICKET_CERRADO    
    Given un ticket existente cerrado
    When el ingeniero de soporte cambie la persona asignada
    Then el sistema indica que no se puede cambiar la persona asignada a un ticket cerrado

    Scenario: PERSONA_ASIGNADA_INEXISTENTE
    Given un ticket existente no cerrado y los datos de una persona "inexistente" en el sistema
    When el ingeniero de soporte cambie la persona asignada
    Then el sistema no registrara la persona asignada en el ticket y solicitara que ingrse los datos de una persona existente en la empresa