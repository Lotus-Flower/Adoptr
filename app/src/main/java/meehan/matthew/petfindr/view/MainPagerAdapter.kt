package meehan.matthew.petfindr.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList: MutableList<Fragment> = mutableListOf()

    private val fragmentMap = hashMapOf<Fragment, String>()

    fun addFragment(title: String, fragment: Fragment) {
        fragmentMap[fragment] = title
        fragmentList.add(fragment)
    }

    fun getFragmentTitle(index: Int) : String? {
        return fragmentMap[fragmentList[index]]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}