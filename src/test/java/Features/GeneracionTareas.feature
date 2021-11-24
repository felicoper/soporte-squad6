Feature: Generacion tareas de proyecto

  Scenario: Tarea al ticket exitosa
    Given un ticket no cerrado, un proyecto existente, y los datos obligatorios para un ticket
    When el ingeniero de soporte cree para un ticket la nueva tarea en un proyecto
    Then el sistema registrará sobre el ticket una nueva tarea, cambiando la persona asignada al ticket por el responsable de la tarea

  Scenario: Proyecto inexistente para ticket
    Given un ticket no cerrado, un proyecto no existente, y los datos obligatorios para un ticket
    When el ingeniero de soporte cree para un ticket la nueva tarea en un proyecto
    Then el sistema no creará la tarea
    And solicitará que ingrese el nombre de un proyecto existente

  Scenario: Ticket cerrado sin nueva tarea
    Given un ticket cerrado, un proyecto existente, y los datos obligatorios para un ticket
    When el ingeniero de soporte cree para un ticket la nueva tarea en un proyecto
    Then el sistema no creará la tarea
    And informará que no es posible crear una tarea a un ticket cerrado