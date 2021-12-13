Feature: Consultar un ticket

  Scenario: CONSULTA_TICKETS_DISPONIBLES_EN_VERSION_DE_PRODUCTO
    Given Hay tickets asociados a una versión de producto determinada
    When El ingeniero de soporte consulte los tickets de una version de producto
    Then El sistema muestra el conjunto de todos los tickets registrados

  Scenario: CONSULTA_SIN_TICKETS_DISPONIBLES_EN_VERSION_DE_PRODUCTO
    Given No hay tickets asociados a una versión de producto determinada
    When El ingeniero de soporte consulte los tickets de una version de producto
    Then El sistema mostrara un mensaje donde explica que no hay tickets registrados en el sistema para esa version del producto
