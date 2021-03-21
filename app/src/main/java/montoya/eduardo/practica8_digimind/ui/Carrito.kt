package montoya.eduardo.practica8_digimind.ui

import java.io.Serializable

class Carrito : Serializable {
    var recordatorios = ArrayList<Recordatorio>()

    fun agregar(recordatorio: Recordatorio): Boolean{
        return  recordatorios.add(recordatorio)
    }
}