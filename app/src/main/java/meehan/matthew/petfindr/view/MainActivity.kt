package meehan.matthew.petfindr.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import meehan.matthew.petfindr.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup ActionBar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.title = ""

        //Setup Navigation
        NavigationUI.setupWithNavController(toolbar, findNavController(R.id.nav_host_fragment), AppBarConfiguration(setOf(R.id.currentPetFragment)))
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, _, _ ->
            toolbar.title = ""
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController(R.id.nav_host_fragment)) ||
                super.onOptionsItemSelected(item)
    }
}
