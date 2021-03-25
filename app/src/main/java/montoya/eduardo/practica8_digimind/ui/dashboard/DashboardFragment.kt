package montoya.eduardo.practica8_digimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import montoya.eduardo.practica8_digimind.R
import montoya.eduardo.practica8_digimind.ui.Recordatorio
import montoya.eduardo.practica8_digimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)


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
            var dias: String = ""

            if(Checkbox1.isChecked)
                aux.add("Monday")

            if(Checkbox2.isChecked)
                aux.add("Tuesday")

            if(Checkbox3.isChecked)
                aux.add("Wednesday")

            if(Checkbox4.isChecked)
                aux.add("Thursday")

            if(Checkbox5.isChecked)
                aux.add("Friday")

            if(Checkbox6.isChecked)
                aux.add("Saturday")

            if(Checkbox7.isChecked)
                aux.add("Sunday")

            if (aux.size == 7)
                dias = "Everyday"

            else if (aux.size == 2) {
                if (aux.contains("Saturday") && aux.contains("Sunday"))
                    dias = "Weekend"

                else{
                    for (i in aux.indices){
                        dias += aux[i]
                        if (i < aux.size-1)
                            dias += ", "
                    }
                }
            }

            else if (aux.size == 5) {
                if (aux.contains("Monday") &&
                        aux.contains("Tuesday") &&
                        aux.contains("Wednesday")&&
                        aux.contains("Thursday")&&
                        aux.contains("Friday"))
                    dias = "Week"

                else {
                    for (i in aux.indices) {
                        dias += aux[i]
                        if (i < aux.size - 1)
                            dias += ", "
                    }
                }
            }

            else{
                for (i in aux.indices){
                    dias += aux[i]
                    if (i < aux.size-1)
                        dias += ", "
                }
            }

            val rec: Recordatorio = Recordatorio(nombre,dias,tiempo)

            HomeFragment.rec.add(rec)

            Toast.makeText(context, "New task added", Toast.LENGTH_SHORT).show()

        }
        return root
    }

}