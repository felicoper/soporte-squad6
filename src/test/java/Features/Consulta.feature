Feature: Consultar un ticket

  Scenario: consulta de tickets disponibles
    Given hay tickets registrados
    When el ingeniero de soporte consulte los tickets
    Then el sistema muestra el conjunto de todos los tickets registrados

  Scenario: consulta de tickets sin ninguno disponible
    Given no hay tickets registrados
    When el ingeniero de soporte consulte los tickets
    Then el sistema mostrara un mensaje donde eplica que no hay tickets registrados en el sistema

  Scenario: consulta de tickets disponibles en version del producto
    Given hay tickets registrados sobre una version de producto
    When el ingeniero de soporte consulte los tickets de una version del producto
    Then el sistema muestra el conjunto de todos os tickers registrados a la version del producto detallada

  Scenario: consulta de la version del producto sin ninguno disponible
    Given hay tickets registrados pero no sobre un version del producto
    When el ingeniero de soporte consulte los tickets asociados a una version de un producto
    Then el sistema muestra un mensaje donde explica que no hay ningun ticket asociado a esa version de producto