Feature: Creacion de ticket
  "Como ingeniero de soporte, quiero crear un nuevo ticket, para mantener un registro de los reclamos con los productos."

  Scenario: CREACION_TICKET_EXITOSO
    Given Los datos validos y obligatorios ingresados para un nuevo ticket
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "registrara" el nuevo ticket
    #And Indica un mensaje de exito con identificador y fecha de creacion

  Scenario: DATO_OBLIGATORIO_INCOMPLETO
    Given Se ingresaron datos para el ticket pero con algun campo obligatorio faltante
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "no registrara" el nuevo ticket
    And Solicitara que ingrese los datos obligatorios faltantes

  Scenario: CLIENTE_NO_EXISTENTE
    Given Los datos obligatorios ingresados para un nuevo ticket pero con "información de cliente inexistente" en la empresa
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "no registrara" el nuevo ticket
    And Solicitará que ingrese los datos de "un cliente" existente en la empresa.

  Scenario: PERSONA_ASIGNADA_NO_EXISTENTE
    Given Los datos obligatorios ingresados para un nuevo ticket pero con "una persona asignada inexistente" en la empresa 
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "no registrara" el nuevo ticket
    And Solicitará que ingrese los datos de "una persona" existente en la empresa.

  Scenario: PRODUCTO_NO_EXISTENTE
    Given Los datos obligatorios ingresados para un nuevo ticket pero con "un producto inexistente" en la empresa
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "no registrara" el nuevo ticket
    And Solicitará que ingrese los datos de "un producto" existente en la empresa.

  Scenario: LEGAJO_CLIENTE_MENOR_A_CERO
    Given Los datos obligatorios ingresados para un nuevo ticket pero con el "número de legajo del cliente" menor a cero
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "no registrara" el nuevo ticket
    And Solicitará que reingrese "el legajo del cliente" correctamente.

  Scenario: LEGAJO_PERSONA_ASIGNADA_MENOR_A_CERO
    Given Los datos obligatorios ingresados para un nuevo ticket pero con el "número de legajo de la persona asignada" menor a cero
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "no registrara" el nuevo ticket
    And Solicitará que reingrese "el legajo de la persona asignada" correctamente.

  Scenario: CARGA_ID_PRODUCTO_MENOR_A_CERO
    Given Los datos obligatorios ingresados para un nuevo ticket pero con el "id del producto" menor a cero
    When El ingeniero de soporte crea un nuevo ticket con los datos
    Then El sistema "no registrara" el nuevo ticket
    And Solicitará que reingrese "el id del producto" correctamente.
