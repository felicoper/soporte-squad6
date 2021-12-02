Feature: Creacion de ticket
  asd

  Scenario: Creacion exitosa de un ticket
    Given Los datos validos y obligatorios ingresados para un nuevo ticket
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema registrara el nuevo ticket
    And Indica un mensaje de exito con identificador y fecha de creacion
