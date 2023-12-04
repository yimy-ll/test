Feature: Ver Listado de Productos

  Como miembro del área de soporte,
  Quiero poder ver un listado de todos los productos al entrar al módulo de soporte,
  Para tener una visión simple y organizada de los productos existentes.

  Scenario: El colaborador de soporte entra al listado de productos y se le informa de los productos existentes
    Given hay productos creados
    When el colaborador de soporte visualiza el listado de productos
    Then se le mostrará el listado de los productos

  Scenario: El colaborador de soporte entra al listado de productos y no apareció ningún producto porque no hay ninguno existente
    Given no hay productos creados
    When el colaborador de soporte visualiza el listado de productos
    Then no se mostrará ningún producto