Feature: Modificar Ticket

  Como Colaborador de Soporte,
  Quiero modificar un ticket,
  Para corregir los datos del mismo en caso de un error, inconveniente o cambio.

  Background:
    Given existen al menos un producto con una versión con un ticket

  Scenario: El Colaborador de soporte modifica el ticket exitosamente
    Given hay un ticket creado
    When el Colaborador de soporte modifica el título
    And el Colaborador de soporte modifica la descripción
    And el Colaborador de soporte guarda los cambios realizados
    Then se le informará que el ticket se ha modificado con éxito

  Scenario: El Colaborador de soporte ingresa valores nulos
    Given hay un ticket creado
    When el Colaborador de soporte modifica el título a null
    And el Colaborador de soporte guarda los cambios realizados
    Then se le informará que hay campos vacíos y se le pedirá que complete todos los campos

  Scenario: El Colaborador de soporte no hizo ningún cambio
    Given hay un ticket creado
    When el Colaborador de soporte guarda los cambios realizados
    Then se le informará de todas maneras que el ticket se ha modificado con éxito