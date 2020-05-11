package meehan.matthew.petfindr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_current_pet.*
import meehan.matthew.petfindr.R
import meehan.matthew.petfindr.application.BaseApp
import meehan.matthew.petfindr.databinding.FragmentCurrentPetBinding
import meehan.matthew.petfindr.viewModel.CurrentPetViewModel
import meehan.matthew.petfindr.dependencyInjection.ViewModelFactory
import meehan.matthew.petfindr.model.remote.AnimalsItemResponse
import meehan.matthew.petfindr.utils.StringHelper
import javax.inject.Inject

class CurrentPetFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CurrentPetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as BaseApp).appComponent?.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CurrentPetViewModel::class.java)

        attachObservers()
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

        setOnClickListeners()
    }

    override fun onStart() {
        super.onStart()

        viewModel.getPets()
    }

    private fun setOnClickListeners() {

        current_pet_save_button.setOnClickListener {
            viewModel.getNextPet()
        }

        current_pet_skip_button.setOnClickListener {
            viewModel.getNextPet()
        }

        current_pet_image_view.setOnClickListener {
        }

    }

    private fun attachObservers() {
        /*viewModel.currentPet.observe(this, Observer {
            it?.let {
                displayCardDetails(it)
            }
        })*/
    }

    private fun displayCardDetails(currentPet: AnimalsItemResponse) {
        currentPet.photos?.firstOrNull()?.let {
            Picasso.get().load(it.full).into(current_pet_image_view)
        }

        pet_description_text_view.text = StringHelper.getPetQuickDescription(currentPet)
    }
}