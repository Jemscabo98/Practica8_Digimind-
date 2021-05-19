package montoya.eduardo.practica8_digimind.ui

import java.io.Serializable
import java.util.ArrayList

data class Recordatorio (var nombre: String?,
                         var dias: ArrayList<String>,
                         var tiempo: String?) : Serializable