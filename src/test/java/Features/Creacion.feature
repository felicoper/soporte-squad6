Feature: Creacion de ticket

  Scenario: Creacion exitosa de un ticket
    Given los datos validos y obligatorios ingresados para un nuevo ticket
    When el ingeniero de soporte crea un nuevo ticket con los datos
    Then el sistema registrara el nuevo ticket
    And indica un mensaje de exito con identificador, fecha de creacion y opcion de crearle una nueva tarea al ticket


  Scenario: Dato obligatorio incompleto
    Given se ingresaron datos para el ticket pero con algun dato obligatorio faltante
    When el ingeniero de soporte crea un nuevo ticket con los datos
    Then el sistema no registrara el nuevo ticket
    And solicitara que ingrese los datos obligatorios




  Scenario: Cliente no existe
    Given se ingresan los datos obligatorios pero con informacion de cliente inexistente
    When el ingeniero de soporte crea un nuevo ticket con los datos
    Then el sistema no registrara el nuevo ticket
    And solicitara que ingrese los datos de un cliente existente en la empresa

  Scenario: Persona asignada no existente
    Given se ingresan los datos obligatorios pero con informacion de persona asignada inexistente
    When el ingeniero de soporte crea un nuevo ticket con los datos
    Then el sistema no registrara el nuevo ticket
    And solicitara que ingrese los datos de una persona existente en la empresa

  Scenario: Producto no existente
    Given se ingresan los datos obligatorios pero con informacion de un producto inexistente
    When el ingeniero de soporte crea un nuevo ticket con los datos
    Then el sistema no registrara el nuevo ticket
    And solicitara que ingrese los datos de un producto existente en la empresa



  Scenario: Legajo de cliente menor a cero
    Given se ingresan los datos obligatorios pero con el numero de legajo del cliente menor a cero
    When el ingeniero de soporte crea un nuevo ticket con los datos
    Then el sistema no registrara el nuevo ticket
    And solicitara que reingrese el legajo del cliente correctamente

  Scenario: Legajo de persona asignada menor a cero
    Given se ingresan los datos obligatorios pero con el numero de legajo de la persona asignada menor a cero
    When el ingeniero de soporte crea un nuevo ticket con los datos
    Then el sistema no registrara el nuevo ticket
    And solicitara que reingrese el legajo de la persona asignada correctamente

  Scenario: Carga Id producto menor a cero
    Given se ingresan los datos obligatorios pero con el id del producto menor a cero
    When el ingeniero de soporte crea un nuevo ticket con los datos
    Then el sistema no registrara el nuevo ticket
    And solicitara que reingrese el id del producto correctamente