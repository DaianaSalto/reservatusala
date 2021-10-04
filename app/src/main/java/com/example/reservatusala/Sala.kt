package com.example.reservatusala

class Sala(var id_sala: Int, var info_sala: String) {

    // FALSE - Disponible
    //Se crea un hashmap para manejar las fechas de la sala
    var fechas: HashMap<Int, HashMap<Int, Boolean>> = hashMapOf(
        1 to hashMapOf(1 to false, 2 to false, 3 to false, 4 to false),
        2 to hashMapOf(1 to false, 2 to false, 3 to false, 4 to false),
        3 to hashMapOf(1 to false, 2 to false, 3 to false, 4 to false),
        4 to hashMapOf(1 to false, 2 to false, 3 to false, 4 to false),
        5 to hashMapOf(1 to false, 2 to false, 3 to false, 4 to false)
    )

    // Se recorre el hasmap fechas seteando el valor a false para disponibilizar el turno
    fun prepararSala() {
        fechas.forEach { (idFecha, horarios) ->
            for (turno in horarios) {
                turno.setValue(false)
            }
        }
    }

    //Se recorre el hashmap horarios para mostrar el estado del mismo (True - No disponible / False - Disponible)
    fun mostrarEstadoSala(fecha: Int, infoHorarios: Map<Int, String>) {
        val horarios = fechas.getValue(fecha)
        horarios.forEach { (idHorario, Estado) ->
            if (Estado) println("° ${infoHorarios[idHorario]} - No disponible") else println(
                "° ${infoHorarios[idHorario]} - Disponible."
            )
        }
    }

    // TRUE - Turno tomado
    // Se cierran turnos de la sala en una fecha especifica
    fun cerrarTurnos(fecha: Int) {
        val horarios = fechas.getValue(fecha)
        for (turno in horarios) {
            turno.setValue(true)
        }
    }


    // TRUE - Turno tomado
    // FALSE - Turno libre
    // Se modifica el valor de un turno especifico  recibido por parametro recorriendo el hashmap horarios
    fun tomarTurnos(idFecha: Int, idTurno: Int) : Boolean{
        var confirmado = false
        val horarios = fechas.getValue(idFecha)
        for (turno in horarios) {
            if (turno.key == idTurno) {
                if (!turno.value) {
                    turno.setValue(true)
                    confirmado = true
                }
            }
        }
        return confirmado
    }

    // TRUE - Turno tomado
    // FALSE - Turno libre

    // Se modifica el valor de un turno especifico  recibido por parametro recorriendo el hashmap horarios
    fun liberarTurnos(idFecha: Int, idTurno: Int) : Boolean{
        var confirmado = false
        val horarios = fechas.getValue(idFecha)
        for (turno in horarios) {
            if (turno.key == idTurno) {
                if (turno.value) {
                    turno.setValue(false)
                    confirmado = true
                }
                else
                    println("El estado del turno es: No Reservado. ¿Ha ingresado los datos correctos?")
            }
        }
        return confirmado
    }
}


