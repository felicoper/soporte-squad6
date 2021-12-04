Feature: Creacion de ticket
  "aquí pondría la historia de usuario..."

  Scenario: Creacion exitosa de un ticket
    Given Los datos validos y obligatorios ingresados para un nuevo ticket
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "registrara" el nuevo ticket
    #And Indica un mensaje de exito con identificador y fecha de creacion

  Scenario: Dato obligatorio incompleto
    Given Se ingresaron datos para el ticket pero con algun dato obligatorio faltante
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "no registrara" el nuevo ticket
    #And solicitara que ingrese los datos obligatorios

  ## Faltan agregar todas las otras!!
