package com.example.reservatusala

// Herencia de clase persona
class Usuario(): Persona("", "")  {
    var tipo: String = "Usuario"
    var id_usuario: String = "${nombreCompleto.trim().substringBefore(" ")}${(0..6).random()}${(0..3).random()}${(0..9).random()}${nombreCompleto.trim().substringAfter(" ")}"

    //Getters y setters personalizados, se evalua una condicion y dependiendo del resultado se asigna el valor ingresado
    // sino, se mantiene el declarado por default
    var edad: Int = 0
        get() = field
        set(value) {
            val checkEdad = checkEdad(value)
            if (checkEdad) {
                field = value
            }
        }

    //Metodo para evaluar Edad
    fun checkEdad(edad : Int) : Boolean
    {
        var ok = false
        if (edad > 17) {
            ok = true
        }
        return ok
    }

    //Constructor asignando valores a la clase Persona
    constructor(dni: String, nombreCompleto: String, edad: Int) : this(){
        this.dni = dni
        this.nombreCompleto = nombreCompleto
        this.edad = edad
        this.id_usuario = "${nombreCompleto.trim().substringBefore(" ")}${(0..6).random()}${(0..3).random()}${(0..9).random()}${nombreCompleto.trim().substringAfter(" ")}"
        this.tipo = "Usuario"
    }




    // Sobreescritura de método de la clase Persona
    override fun login() {
        println("--------------------------------------")
        println ("Hola ${nombreCompleto} - ${tipo}: ${id_usuario},")
        super.login()
    }

    // Sobreescritura de método de la clase Persona
    override fun logout() {
        println("--------------------------------------")
        println ("${tipo} ${id_usuario}")
        super.logout()
    }

    // Sobreescritura de método de la clase Persona
    override fun consultaDatos(): String {
        println("--------------------------------------")
        return super.consultaDatos() + "\nTipo: $tipo\nId Usuario: $id_usuario \nEdad: ${if (edad != 0) edad else "Debés ser mayor de 18 años."}"
    }

    // Se solicita una reserva enviando los datos de la instancia actual de usuario
    fun solicitarReserva(sala: Sala, fecha: Int, hora: Int) : (Reserva){
        val res = Reserva(sala, this)
        res.generarReserva(fecha, hora)
        return res
    }
}