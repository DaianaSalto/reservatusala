package com.example.reservatusala

//Clase padre
open class Persona (var dni: String, var nombreCompleto: String) {

    // Metodo con sobreescritura en clases hijas
    open fun consultaDatos(): String {
        println("--------------------------------------")
        return "Datos de persona: \nDNI: $dni\nNombre Completo: $nombreCompleto"
    }

    // Metodo con sobreescritura en clases hijas
    open fun login() {
        print("has realizado el login al sistema exitosamente.\n")
    }

    // Metodo con sobreescritura en clases hijas
    open fun logout() {
        print("ha cerrado sesión.\n")
    }

}