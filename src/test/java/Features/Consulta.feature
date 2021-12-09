Feature: Consultar un ticket

  Scenario: CONSULTA_TICKETS_DISPONIBLES_EN_VERSION_DE_PRODUCTO
    Given hay tickets registrados en el sistema y "sobre una version de producto"
    When el ingeniero de soporte consulte los tickets de una version de producto
    Then el sistema muestra el conjunto de todos los tickets registrados

  Scenario: CONSULTA_SIN_TICKETS_DISPONIBLES_EN_VERSION_DE_PRODUCTO
    Given hay tickets registrados en el sistema y "no sobre una version de producto"
    When el ingeniero de soporte consulte los tickets de una version de producto
    Then el sistema mostrara un mensaje donde explica que no hay tickets registrados en el sistema para esa version del producto
