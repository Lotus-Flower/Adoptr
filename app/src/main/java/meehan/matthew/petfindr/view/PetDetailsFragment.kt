package meehan.matthew.petfindr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pet_details.*
import meehan.matthew.petfindr.R
import meehan.matthew.petfindr.application.BaseApp
import meehan.matthew.petfindr.databinding.FragmentPetDetailsBinding
import meehan.matthew.petfindr.dependencyInjection.ViewModelFactory
import meehan.matthew.petfindr.viewModel.PetDetailsViewModel
import java.lang.Exception
import javax.inject.Inject

class PetDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: PetDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)

        (requireActivity().application as BaseApp).appComponent?.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(PetDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPetDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@PetDetailsFragment
            viewModel = this@PetDetailsFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: PetDetailsFragmentArgs by navArgs()

        pet_details_image_view.transitionName = args.currentPetModel.photoUrl

/*        Picasso.get().load(args.currentPetModel.photoUrl).into(pet_details_image_view, object : Callback{
            override fun onSuccess() {
                startPostponedEnterTransition()
            }

            override fun onError(e: Exception?) {
                startPostponedEnterTransition()
            }

        })*/
        viewModel.currentPet.value = args.currentPetModel
    }
}