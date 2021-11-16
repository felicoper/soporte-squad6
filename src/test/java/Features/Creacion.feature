Feature: Creacion de ticket

  Scenario: Creacion exitosa de un ticket
    Given los datos validos y obligatorios ingresados para un nuevo ticket
    When el ingeniero de soporte crea un nuevo ticket con los datos
    Then el sistema registrara el nuevo ticket
    And indica un mensaje de exito con identificador, fecha de creacion y opcion de crearle una nueva tarea al ticket