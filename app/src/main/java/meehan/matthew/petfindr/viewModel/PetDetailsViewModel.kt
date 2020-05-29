package meehan.matthew.petfindr.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import meehan.matthew.petfindr.model.local.PetModel
import javax.inject.Inject

class PetDetailsViewModel @Inject constructor(): ViewModel() {

    val currentPet: MutableLiveData<PetModel> = MutableLiveData()

}