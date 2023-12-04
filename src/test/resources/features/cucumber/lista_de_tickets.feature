Feature: Ver Listado de Versiones de un Producto

  Como miembro del área de soporte,
  Quiero poder ver un listado de todas las versiones de un producto al entrar a un producto,
  Para tener una visión simple y organizada de las versiones existentes de un producto.

  Background:
    Given que existe al menos un producto con una versión con tickets y otra versión sin tickets

  Scenario: El colaborador de soporte ingresó a un producto y le apareció un listado de versiones existentes
    Given que hay una versión con tickets
    When el colaborador de soporte ingresa al listado de tickets de la versión con tickets
    Then se le mostrará el listado de los tickets de la versión

  Scenario: El colaborador de soporte ingresó a un producto y no le apareció ninguna versión porque no hay ninguna existente
    Given que hay una versión sin tickets
    When el colaborador de soporte ingresa al listado de tickets de la versión sin tickets
    Then se le mostrará un listado vacío dado que no hay tickets para la versión
