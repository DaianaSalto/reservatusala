package com.example.reservatusala

// Salto Daiana - 04/10/21

/* Consignas:
1) Crear clases sobre un modelo que contenga, bajo acoplamiento y alta cohesión, utilizando la relación de
herencia, crear 3 métodos con funcionalidades que le den vida a los objetos, crear objetos que demuestren
el comportamiento de estas funcionalidades, Sobreescribir 3 métodos, sumar a este último punto el método
ToString().
Hasta aquí la calificación sin errores es un 5

2) Crear 3 métodos utilizando el concepto de Sobre carga de operadores con la creación de objetos.
Hasta aquí, sin errores, la calificación es un 7

3) Implementar 2 relaciones de composición y validar 3 propiedades con los Get y Set personalizados llamando
a un método que resuelva la validación.
Hasta aquí la calificación sin errores es un 10
*/


fun main() {
    // Se crea un Estudio

    //Se agregan días y horarios de apertura
    val infoHorarios = hashMapOf(
        1 to "09.00 - 11.00 hs",
        2 to "11.00 - 13.00 hs",
        3 to "15.00 - 18.00 hs",
        4 to "18.00 - 19.00 hs"
    )

    val infoFechas = hashMapOf(
        1 to "Martes 5/10",
        2 to "Miercoles 6/10",
        3 to "Jueves 7/10",
        4 to "Viernes 8/10",
        5 to "Sábado 9/10"
    )

    val estudio = Estudio("Rock 1", "Av. Avellaneda 1212", infoHorarios, infoFechas, "Breve reseña del estudio..." )

    // ------------------------------------------------------------------
    // Se agregan Salas
    val sala1 = Sala(101, "Metraje: 4 x 6 mts - Capacidad 4 personas.")
    val sala2 = Sala(102, "Metraje: 4 x 8 mts.")
    estudio.habilitarSala(sala1)
    estudio.habilitarSala(sala2)

    //Se consultan datos de estudio y salas
    println(estudio.toString())
    estudio.mostrarSalasInfo()


    // Se crea empleado
    val empleado1 = Empleado()

    //Se consultan datos del empleado (sobreescritura de métodos)
    println(empleado1.consultaDatos())

    // Se crea usuario
    val usuario1 = Usuario("12312311", "Coco Lopez", 28 )
    println(usuario1.consultaDatos())

    //Usuario menor de edad para validar getter persolizado:
    val usuarioMenor = Usuario("12354213", "Roberta Sanchez", 16)
    println(usuarioMenor.consultaDatos())



    // ------------------------------------------------------------------


    //Empleado se loguea (sobreescritura de métodos)
    empleado1.login()

    //Empleado cierra sala
    empleado1.cerrarSala(sala1, 3)

    // ------------------------------------------------------------------

    //Usuarios se loguean (sobreescritura de métodos)
    usuario1.login()
    usuarioMenor.login()

    // Usuario reserva salas
    estudio.confirmarReserva(usuario1.solicitarReserva(sala2, 4, 1))
    estudio.confirmarReserva(usuario1.solicitarReserva(sala1, 1, 3))

    // Usuario menor de edad intenta reservar sala
    estudio.confirmarReserva(usuarioMenor.solicitarReserva(sala2, 2, 3))

    // Se consulta el listado de salas del estudio, donde se puede ver que se tomó correctamente el turno
    estudio.listarEstadosSalas()

    // Se consultan las reservas del estudio
    estudio.listarReservas()

    //Consulta de reservas con diferentes datos para mostrar la sobrecarga de métodos aplicada

    //Consulta por numero de reserva (INT)
    val res = estudio.reservas[1].id_reserva
    estudio.consultarReserva(res)

    //Consulta por numero de reserva (String)
    estudio.consultarReserva(usuario1.id_usuario)

    //Consulta por sala, fecha, hora (int, int, int)
    estudio.consultarReserva(102, 2 ,3)

    //Usuario y empleado se desloguean (sobreescritura de métodos)
    empleado1.logout()
    usuario1.logout()

}
