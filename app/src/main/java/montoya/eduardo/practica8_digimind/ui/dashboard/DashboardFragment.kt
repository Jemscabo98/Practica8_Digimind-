package montoya.eduardo.practica8_digimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import montoya.eduardo.practica8_digimind.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()

        val firstAnswer: TextView = root.findViewById(R.id.firstAnswer)
        val btnSetTime: Button = root.findViewById(R.id.btnSetTime)
        val Checkbox1: CheckBox = root.findViewById(R.id.Checkbox1)
        val Checkbox2: CheckBox = root.findViewById(R.id.Checkbox2)
        val Checkbox3: CheckBox = root.findViewById(R.id.Checkbox3)
        val Checkbox4: CheckBox = root.findViewById(R.id.Checkbox4)
        val Checkbox5: CheckBox = root.findViewById(R.id.Checkbox5)
        val Checkbox6: CheckBox = root.findViewById(R.id.Checkbox6)
        val Checkbox7: CheckBox = root.findViewById(R.id.Checkbox7)
        val btnRegistro: Button = root.findViewById(R.id.btnRegistro)
        var tiempo = ""

        btnSetTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{view: TimePicker, hour: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                tiempo = SimpleDateFormat("HH:mm").format(cal.time)
                btnSetTime.text = tiempo
                Toast.makeText(context, tiempo, Toast.LENGTH_LONG).show()
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        btnRegistro.setOnClickListener {

            val nombre = firstAnswer.text.toString()
            var aux: ArrayList<String> = ArrayList<String>()

            val actividad = hashMapOf(
                "accion" to nombre,
                "email" to usuario.currentUser?.email.toString(),
                "lunes" to Checkbox1.isChecked,
                "martes" to Checkbox2.isChecked,
                "miercoles" to Checkbox3.isChecked,
                "jueves" to Checkbox4.isChecked,
                "viernes" to Checkbox5.isChecked,
                "sabado" to Checkbox6.isChecked,
                "domingo" to Checkbox7.isChecked,
                "tiempo" to btnSetTime.toString())

            storage.collection("actividades")
                    .add(actividad)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Task Added", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(context, "Task Failed", Toast.LENGTH_LONG).show()
                    }
        }
        return root
    }

}