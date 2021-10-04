package com.example.reservatusala

class Estudio(
    var nombre: String,
    var domicilio: String,
    val infoHorarios: Map<Int, String>, // Permite agregar la cantidad necesaria de horarios
    var infoFechas: Map<Int, String>, // Permite agregar la cantidad necesaria de fechas, al igual que con infoHorarios, pudiendo habilitar x cantidad de dìas segun la instancia
    var info: String
    ) {
    var salas: MutableList<Sala> = ArrayList()
    var reservas: MutableList<Reserva> = ArrayList()

    //Se sobreescribe el método toString para imprimir por consola datos de las instancias de Estudio
    override fun toString(): String {
        return "Bienvenido a Estudio $nombre. \n" +
                "Domicilio: $domicilio. \n" +
                "Días de apertura: ${
                    infoFechas[1]!!.trim().substringBefore(" ")
                } a ${infoFechas[5]!!.trim().substringBefore(" ")}.\n" +
                "Horarios: ${
                    infoHorarios[1]!!.trim().substringBefore(" ")
                } a ${infoHorarios[4]!!.trim().substringAfter("- ")}.\n" +
                "${if (info.isEmpty()) "No hay información extra para mostrar." else "Información extra: '$info'"}\n" +
                "--------------------------------------"

    }

    //Se agregan objetos Sala al array de salas
    fun habilitarSala(nuevaSala: Sala) {
        nuevaSala.prepararSala()
        salas.add(nuevaSala)
    }

    //Se recorre el array de objetos salas que tiene el Estudio para mostrar los datos de cada objeto
    fun mostrarSalasInfo() {
        println("Listado de Salas del Estudio")
        println("--------------------------------------")
        for (sala in salas) {
            println("Sala: ${sala.id_sala}\nInformación: ${sala.info_sala}")
            println("--------------------------------------")
        }
    }

    //Se recorre primero el hashmap de fechas y en cada iteracion
    // se recorre el listado de salas mostrando el id de la misma y llamando a un método de dicha
    // clase para consultar sus datos de turnos disponibles
    fun listarEstadosSalas() {
        println("Listado de Turnos por Sala")
        for (fecha in infoFechas) {
            println("--------------------------------------")
            println("      ***** ${fecha.value} *****")
            for (sala in salas) {
                println("Sala: ${sala.id_sala}")
                sala.mostrarEstadoSala(fecha.key, infoHorarios)
            }
        }

    }

    // Valida que el usuario sea mayor de edad y la reserva esté OK, luego confirma
    // la reserva agregando al array de reservas esta nueva instancia
    fun confirmarReserva(nuevaRes: Reserva) {
        if (nuevaRes.usuario.edad != 0 && nuevaRes.horario != 0) {
            reservas.add(nuevaRes)
            println(
                "¡${nuevaRes.usuario.nombreCompleto}, tu reserva ha sido confirmada!\n" +
                        "--------------------------------------\n" +
                        "Usuario: ${nuevaRes.usuario.id_usuario}\n" +
                        "Numero de Reserva: ${nuevaRes.id_reserva}\n" +
                        "Sala: ${nuevaRes.sala.id_sala}\nFecha: ${infoFechas[nuevaRes.fecha]}\nHorario: ${infoHorarios[nuevaRes.horario]}"
            )
        }
    }

    // Se muestran por consola los datos de cada instancia de la clase Reserva almacenada en el array de reservas
    fun listarReservas() {

        println("Listado de Reservas")
        for (item in reservas) {
            println("--------------------------------------")
            println(
                "      ***** Reserva ${item.id_reserva} *****\n" +
                        "Sala: ${item.sala.id_sala}\n" +
                        "Fecha: ${infoFechas[item.fecha]} - Turno: ${infoHorarios[item.horario]}\n" +
                        "Usuario: ${item.usuario.id_usuario}\n"
            )
        }
    }

    // Sobrecarga de métodos

    // Consultar reserva con id de reserva - Recibe un Int, recorre el array de reservas e imprime los datos si los encuentra
    fun consultarReserva(reserva: Int) {
        var checkRes = false
        println("Buscando la reserva $reserva . . . ")
        for (item in reservas) {
            if (item.id_reserva == reserva) {
                println("Reserva encontrada!")
                println("--------------------------------------")
                println("Datos de tu reserva:")
                println(
                    "      ***** Reserva ${item.id_reserva} *****\n" +
                            "Fecha: ${infoFechas[item.fecha]} - Turno: ${infoHorarios[item.horario]}\n" +
                            "Sala: ${item.sala.id_sala} - Usuario: ${item.usuario.nombreCompleto}\n" +
                            "--------------------------------------")
                checkRes = true
            }
        }
        if (!checkRes) {
            println("--------------------------------------")
            println("No hemos encontrado una reserva con ese número.")
            println("--------------------------------------")
        }
    }


    // Consultar reserva con id de usuario - Recibe un Strig, recorre el array de
    // reservas, busca un id de usuario e imprime los datos si los encuentra
    fun consultarReserva(usuario: String) {
        var checkRes = false
        println("Buscando reservas del usuario $usuario . . . ")
        for (item in reservas) {
            if (item.usuario.id_usuario == usuario) {
                println("Reserva encontrada!")
                println("--------------------------------------")
                println("Datos de tu reserva:")
                println(
                    "      ***** Reserva ${item.id_reserva} *****\n" +
                            "Fecha: ${infoFechas[item.fecha]} - Turno: ${infoHorarios[item.horario]}\n" +
                            "Sala: ${item.sala.id_sala} - Usuario: ${item.usuario.nombreCompleto}\n" +
                            "--------------------------------------")
                checkRes = true
            }
        }
        if (!checkRes) {
            println("--------------------------------------")
            println("No hemos encontrado reservas a tu nombre.")
            println("--------------------------------------")
        }
    }

    // Consultar reserva con id fecha y hora de reserva - Recibe un 3 int, recorre el array de reservas,
    // busca el id de sala, la fecha y el horario e imprime los datos si los encuentra
    fun consultarReserva(sala: Int, fecha: Int, hora: Int) {
        var checkRes = false
        println("Buscando reserva en la sala $sala - Fecha: $fecha - Hora: $hora . . . ")
        for (item in reservas) {
            if (item.sala.id_sala == sala && item.fecha == fecha && item.horario == hora) {
                println("Reserva encontrada!")
                println("--------------------------------------")
                println("Datos de tu reserva:")
                println(
                    "      ***** Reserva ${item.id_reserva} *****\n" +
                            "Fecha: ${infoFechas[item.fecha]} - Turno: ${infoHorarios[item.horario]}\n" +
                            "Sala: ${item.sala.id_sala} - Usuario: ${item.usuario.nombreCompleto}\n" +
                            "--------------------------------------")
                checkRes = true
            }
        }
        if (!checkRes) {
            println("--------------------------------------")
            println("No hemos encontrado reservas con esos datos.")
            println("--------------------------------------")
        }

    }
}







