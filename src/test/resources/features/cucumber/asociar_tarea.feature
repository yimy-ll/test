Feature: Asociar Tarea a Ticket

  Como miembro del área de soporte,
  Quiero asociar una tarea a un ticket,
  Para relacionar el problema del ticket con una tarea.

  Background:
    Given que existe un ticket

  Scenario: El colaborador de soporte asoció una tarea a un ticket exitosamente
    Given que hay una tarea
    When el colaborador de soporte intenta asociar la tarea al ticket
    Then se le informará que la tarea se asoció correctamente al ticket indicado

  Scenario: El colaborador de soporte intenta asociar una tarea a un ticket pero no hay ninguna tarea creada
    Given que no hay tareas existentes
    When el colaborador de soporte intenta asociar la tarea al ticket
    Then se le informará que no hay tareas existentes, por lo que no se puede asociar al ticket

  Scenario: El colaborador de soporte intenta asociar una tarea a un ticket al que ya estaba asociado previamente
    Given hay una tarea asociada a un ticket
    When el colaborador de soporte intenta asociar la tarea al ticket
    Then no se hará nada ya que la tarea es la misma a la que estaba asociado anteriormente

