package com.example.reservatusala

// Herencia de clase persona, se pasan los valores a la clase padre directamente en la creacion de la clase
class Empleado: Persona("1231233", "Pepe Herrero") {

    var tipo: String = "Empleado"
    var legajo: String = "100${(0..9).random()}${(0..9).random()}${(0..9).random()}"

    // Sobreescritura de método de la clase Persona
    override fun login() {
        println("--------------------------------------")
        println ("Hola ${nombreCompleto} - ${tipo} - Legajo Num: ${legajo}, ")
        super.login()
    }

    // Sobreescritura de método de la clase Persona
    override fun logout() {
        println("--------------------------------------")
        println ("${tipo} ${legajo}")
        super.logout()
    }

    // Sobreescritura de método de la clase Persona
    override fun consultaDatos(): String {
        return super.consultaDatos() + "\nTipo: $tipo\nLegajo: $legajo"
    }

    //Se cierra una sala en una fecha especifica
    fun cerrarSala(sala: Sala, fecha: Int){
            sala.cerrarTurnos(fecha)
            println ("Sala ${sala.id_sala} cerrada por empleado: ${legajo}.")
            println("--------------------------------------")
    }
}