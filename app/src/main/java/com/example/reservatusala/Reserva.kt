package com.example.reservatusala

class Reserva(
    var sala: Sala, //Relación de composición
    var usuario: Usuario) //Relación de composición
{
    var id_reserva: Int = ("100${(0..9).random()}${(0..9).random()}${(0..9).random()}").toInt()

    //Getters y setters personalizados, en ambos se evalua una condicion
    // y dependiendo del resultado se asigna el valor ingresado, sino, se mantiene el declarado por default
    var fecha: Int = 0
        get() = field
        set(value) { if (!checkFecha(value)) field = value }

    var horario: Int = 0
        get() = field
        set(value) { if (checkHorario(value)) field = value }


    //Metodos para evaluar getters y setters
    fun checkFecha(fecha : Int) : Boolean
    {
        var ok = false
        if (fecha > sala.fechas.size) {
            ok = true
        }
        return ok
    }

    fun checkHorario(horario: Int) : Boolean
    {
        var ok = false
        val horarios = sala.fechas.getValue(fecha)

        if (horarios.keys.contains(horario) && !horarios[horario]!!) {
            ok = true
        }
        return ok
    }


    // Se evaluan condiciones para restringir la toma de turnos
    fun check(): String {
        var data : String = ""
        if (this.fecha == 0) {
            data = "No hay salas habilitadas en esta fecha."
        }
        if (this.horario == 0) {
            data = "La sala seleccionada no está habilitada en ese horario."
        }

        return data
    }

    // Se confirma reserva o notifica que no es posible realizarlo evaluando la edad del usuario
    fun generarReserva(fechaElegida: Int, horaElegida: Int) {
        this.fecha = fechaElegida
        this.horario = horaElegida
        if (usuario.edad != 0) {
            val reservaOK = sala.tomarTurnos(fecha, horario)
            if (reservaOK) {
                println(check())
            }
        }
        else println("-----------------------------------------------\n" +
                "${usuario.nombreCompleto}, debés ser mayor de 18 años para reservar.\n")

    }
}