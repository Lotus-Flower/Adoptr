package meehan.matthew.petfindr.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import meehan.matthew.petfindr.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.title = ""
        NavigationUI.setupWithNavController(toolbar, findNavController(R.id.nav_host_fragment), AppBarConfiguration(setOf(R.id.currentPetFragment)))
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, _, _ ->
            toolbar.title = ""
        }

        setupUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun setupUI() {

        val adapter = MainPagerAdapter(
            supportFragmentManager,
            lifecycle
        ).apply {
            addFragment("Pet", CurrentPetFragment())
            addFragment("Saved", SavedPetsFragment())
            addFragment("Profile", ProfileFragment())
        }

        //main_view_pager.adapter = adapter
        //main_view_pager.isUserInputEnabled = false

        /*TabLayoutMediator(main_tab_layout, main_view_pager) { tab: TabLayout.Tab, i: Int ->
            tab.text = adapter.getFragmentTitle(i)
        }.attach()*/

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController(R.id.nav_host_fragment)) ||
                super.onOptionsItemSelected(item)
    }
}
