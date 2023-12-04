Feature: Ver Detalle de un Ticket

  Como miembro del área de soporte,
  Quiero poder ver un ticket en detalle
  Para ver de forma más completa todos los datos relacionados con el mismo.

  Background:
    Given que existe al menos un producto con una versión

  Scenario: El colaborador de soporte pudo seleccionar un ticket existente
    Given que existe al menos un ticket
    When el colaborador de soporte ingresa al detalle del ticket
    Then se le mostrarán todos los datos relacionados al ticket

  Scenario: El colaborador de soporte no pudo seleccionar ningún ticket porque no existe ninguno
    Given que no hay tickets existentes
    When el colaborador de soporte ingresa al detalle del ticket
    Then no se le mostrará nada