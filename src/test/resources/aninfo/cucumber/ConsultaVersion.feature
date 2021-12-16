Feature: Consultar version de un producto

    Scenario: CONSULTA_VERSION_DE_PRODUCTO
    Given Hay versiones de un producto registrados en sistema
    When El ingeniero de soporte consulte las versiones de un producto
    Then El sistema muestra el conjunto de todas las versiones asignadas al producto


    Scenario: CONSULTA_VERSION_PRODUCTO_INEXISTENTE
    Given Hay un producto inexistente
    When El ingeniero de soporte consulte las versiones de un producto
    Then El sistema muestra un mensaje donde explica que no existe ese producto en la empresa 