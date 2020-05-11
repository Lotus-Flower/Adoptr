package meehan.matthew.petfindr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import meehan.matthew.petfindr.R
import meehan.matthew.petfindr.application.BaseApp
import meehan.matthew.petfindr.dependencyInjection.ViewModelFactory
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

}