package montoya.eduardo.practica8_digimind.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import montoya.eduardo.practica8_digimind.R
import montoya.eduardo.practica8_digimind.ui.Communicator
import montoya.eduardo.practica8_digimind.ui.Recordatorio

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        var comm = activity as Communicator


        val firstAnswer: TextView = root.findViewById(R.id.firstAnswer)
        val thirdAnswer: TextView = root.findViewById(R.id.thirdAnswer)
        val Checkbox1: CheckBox = root.findViewById(R.id.Checkbox1)
        val Checkbox2: CheckBox = root.findViewById(R.id.Checkbox2)
        val Checkbox3: CheckBox = root.findViewById(R.id.Checkbox3)
        val Checkbox4: CheckBox = root.findViewById(R.id.Checkbox4)
        val Checkbox5: CheckBox = root.findViewById(R.id.Checkbox5)
        val Checkbox6: CheckBox = root.findViewById(R.id.Checkbox6)
        val Checkbox7: CheckBox = root.findViewById(R.id.Checkbox7)
        val btnRegistro: Button = root.findViewById(R.id.btnRegistro)


        btnRegistro.setOnClickListener {
            val nombre = firstAnswer.text.toString()
            val tiempo = thirdAnswer.text.toString()


            var aux: ArrayList<String> = ArrayList<String>()
            var dias: String = ""

            if(Checkbox1.isSelected)
                aux.add("Monday")

            if(Checkbox2.isSelected)
                aux.add("Tuesday")

            if(Checkbox3.isSelected)
                aux.add("Wednesday")

            if(Checkbox4.isSelected)
                aux.add("Thursday")

            if(Checkbox5.isSelected)
                aux.add("Friday")

            if(Checkbox6.isSelected)
                aux.add("Saturday")

            if(Checkbox7.isSelected)
                aux.add("Sunday")

            if (aux.size == 7)
                dias = "Everyday"

            else if (aux.size == 2) {
                if (aux.contains("Saturday") && aux.contains("Sunday"))
                    dias = "Weekend"
            }

            else if (aux.size == 5) {
                if (aux.contains("Monday") &&
                        aux.contains("Tuesday") &&
                        aux.contains("Wednesday")&&
                        aux.contains("Thursday")&&
                        aux.contains("Friday"))
                    dias = "Week"
            }

            else{
                for (i in aux.indices){
                    dias += aux[i]
                    if (i < aux.size)
                        dias += ", "
                }
            }

            comm.passData(nombre,dias,tiempo)
        }
        return root
    }

}