Feature: Ver Listado de Versiones de un Producto

  Como miembro del área de soporte,
  Quiero poder ver un listado de todas las versiones de un producto al entrar a un producto,
  Para tener una visión simple y organizada de las versiones existentes de un producto.

  Background:
    Given existe al menos un producto

  Scenario: El colaborador de soporte ingresó a un producto y le apareció un listado de versiones existentes
    Given que hay un producto con versiones
    When el colaborador de soporte ingresa al listado de versiones del producto con versiones
    Then se le mostrará el listado de las versiones del producto

  Scenario: El colaborador de soporte ingresó a un producto y no le apareció ninguna versión porque no hay ninguna existente
    Given que hay un producto sin versiones
    When el colaborador de soporte ingresa al listado de versiones del producto sin versiones
    Then se le mostrará un listado vacío dado que no hay versiones para el producto