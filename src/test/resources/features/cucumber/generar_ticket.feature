Feature: Generar un ticket

  Como Colaborador de Soporte,
  Quiero generar un ticket,
  Para tener registro de todas las quejas realizadas por los usuarios.

  Background:
    Given existe al menos un producto con una versión

  Scenario: El colaborador de soporte genera un ticket exitosamente
    Given se completaron los datos correspondientes para crear un ticket de una queja pendiente
    When el colaborador de soporte intenta generar un ticket
    Then se le informará que el ticket se ha generado con éxito y se generará.

  Scenario: El colaborador de soporte intenta generar un ticket pero no completó todos los datos
    Given hay campos que no se completaron al generar un ticket de una queja
    When el colaborador de soporte intenta generar un ticket
    Then se le informará que hay campos que no han sido completados, y se pedirá que los complete.