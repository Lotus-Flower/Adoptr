package meehan.matthew.petfindr.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import meehan.matthew.petfindr.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
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

        main_view_pager.adapter = adapter
        main_view_pager.isUserInputEnabled = false

        TabLayoutMediator(main_tab_layout, main_view_pager) { tab: TabLayout.Tab, i: Int ->
            tab.text = adapter.getFragmentTitle(i)
        }.attach()

    }
}
