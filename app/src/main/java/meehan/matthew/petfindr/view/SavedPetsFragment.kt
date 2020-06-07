package meehan.matthew.petfindr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_saved_pets.*
import meehan.matthew.petfindr.R
import meehan.matthew.petfindr.application.BaseApp
import meehan.matthew.petfindr.dependencyInjection.ViewModelFactory
import meehan.matthew.petfindr.model.local.PetModel
import meehan.matthew.petfindr.viewModel.SavedPetsViewModel
import javax.inject.Inject

class SavedPetsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SavedPetsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as BaseApp).appComponent?.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SavedPetsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_pets, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPetsByIds()
    }

    override fun onStart() {
        super.onStart()

        val linearLayoutManager = LinearLayoutManager(this@SavedPetsFragment.context)

        saved_pets_recycler.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(this.context, linearLayoutManager.orientation))
            postponeEnterTransition()
            viewTreeObserver.addOnDrawListener {
                startPostponedEnterTransition()
            }
            adapter = SavedPetsAdapter(mutableListOf(), { petModel, imageView ->
                val extras = FragmentNavigatorExtras(
                    imageView to (petModel?.photoUrl ?: "")
                )
                navigateToPetDetails(petModel, extras)
            }, {
                viewModel.removePet(it)
            })
        }

        attachObservers()
    }

    private fun attachObservers() {
        viewModel.savedPetsList.observe(this, Observer {
            (saved_pets_recycler.adapter as SavedPetsAdapter).update(it)
        })
    }

    private fun navigateToPetDetails(currentPet: PetModel?, extras: FragmentNavigator.Extras) {
        currentPet?.let {
            findNavController().navigate(SavedPetsFragmentDirections.actionSavedPetsFragmentToPetDetailFragment(it), extras)
        }
    }

}