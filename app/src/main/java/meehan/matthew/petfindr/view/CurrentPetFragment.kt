package meehan.matthew.petfindr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_current_pet.*
import meehan.matthew.petfindr.application.BaseApp
import meehan.matthew.petfindr.databinding.FragmentCurrentPetBinding
import meehan.matthew.petfindr.dependencyInjection.ViewModelFactory
import meehan.matthew.petfindr.viewModel.CurrentPetViewModel
import javax.inject.Inject

class CurrentPetFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CurrentPetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as BaseApp).appComponent?.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CurrentPetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCurrentPetBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@CurrentPetFragment
            viewmodel = this@CurrentPetFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachObservers()
    }

    private fun attachObservers() {
        viewModel.detailsViewClickedObservable.observe(this, Observer {
            navigateToPetDetails()
        })
    }

    private fun navigateToPetDetails() {
        viewModel.currentPet.value?.let {
            val extras = FragmentNavigatorExtras(
                current_pet_image_view to it.photoUrl
            )
            findNavController().navigate(CurrentPetFragmentDirections.actionCurrentPetFragmentToPetDetailFragment(it), extras)
        }
    }
}