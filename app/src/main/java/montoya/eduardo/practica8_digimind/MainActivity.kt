package montoya.eduardo.practica8_digimind

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import montoya.eduardo.practica8_digimind.ui.Communicator
import montoya.eduardo.practica8_digimind.ui.home.HomeFragment
import montoya.eduardo.practica8_digimind.ui.Recordatorio as Recordatorio1

class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun passData(nombre: String, dias: String, tiempo: String) {
        val bundle = Bundle()
        bundle.putString("nombre", nombre)
        bundle.putString("dias", dias)
        bundle.putString("tiempo", tiempo)

        val transaction = this.supportFragmentManager.beginTransaction()
        val homeFra = HomeFragment()
        homeFra.arguments = bundle

        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}