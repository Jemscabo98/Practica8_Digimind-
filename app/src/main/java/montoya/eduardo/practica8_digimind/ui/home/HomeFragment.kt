package montoya.eduardo.practica8_digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import montoya.eduardo.practica8_digimind.R
import montoya.eduardo.practica8_digimind.ui.Recordatorio
import android.widget.BaseAdapter as BaseAdapter

class HomeFragment : Fragment() {
    var adapter: Adapter? = null
    var rec = ArrayList<Recordatorio>()

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        var listener: HomeFragment
        var gridNote : GridView = root.findViewById(R.id.gridView)

        var nombre = arguments?.getString("nombre")
        var dias = arguments?.getString("dias")
        var tiempo = arguments?.getString("tiempo")

        if (nombre == null)
            nombre = "Practice"
        if (dias == null)
            dias = "Everyday"
        if (tiempo == null)
            tiempo = "17:00"

        rec.add(Recordatorio(nombre, dias, tiempo))

        adapter = Adapter(this.context, rec)
        gridNote.adapter = adapter

        return root
    }
}

class Adapter() : BaseAdapter() {
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