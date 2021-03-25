package montoya.eduardo.practica8_digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import montoya.eduardo.practica8_digimind.R
import montoya.eduardo.practica8_digimind.ui.Recordatorio
import android.widget.BaseAdapter as BaseAdapter

class HomeFragment : Fragment() {
    private var adapter: Adapter? = null

    companion object{
        var rec = ArrayList<Recordatorio>()
        var first = true
    }

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        var gridNote : GridView = root.findViewById(R.id.gridView)

        if(first) {
            fillTasks()
            first = false
        }

        adapter = Adapter(this.context, rec)
        gridNote.adapter = adapter

        return root
    }

    fun fillTasks() {
        rec.add(Recordatorio("Practice 1","Tuesday","17:30"))
        rec.add(Recordatorio("Practice 2","Monday, Saturday","17:00"))
        rec.add(Recordatorio("Practice 3","Wednesday","14:00"))
        rec.add(Recordatorio("Practice 4","Saturday","11:00"))
        rec.add(Recordatorio("Practice 5","Friday","13:00"))
        rec.add(Recordatorio("Practice 6","Thursday","10:40"))
        rec.add(Recordatorio("Practice 7","Monday","12:00"))
    }

    private class Adapter() : BaseAdapter() {
        var rec = ArrayList<Recordatorio>()
        var context: Context? = null

        constructor(context: Context?, rec: ArrayList<Recordatorio>) : this() {
            this.rec = rec
            this.context = context
        }

        override fun getCount(): Int {
            return rec.size
        }

        override fun getItem(position: Int): Any {
            return rec[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var aux = rec[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflater.inflate(R.layout.recordatorio, null)

            var nombre: TextView =  vista.findViewById(R.id.textNombreRecordatorio)
            var dias:   TextView =  vista.findViewById(R.id.textDiasRecordatorio)
            var tiempo: TextView =  vista.findViewById(R.id.textTiempoRecordatorio)

            nombre.setText(aux.nombre)
            dias.setText(aux.dias)
            tiempo.setText(aux.tiempo)

            return vista
        }
    }
}

