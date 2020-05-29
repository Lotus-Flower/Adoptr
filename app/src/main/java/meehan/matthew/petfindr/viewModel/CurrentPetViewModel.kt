package meehan.matthew.petfindr.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import meehan.matthew.petfindr.model.local.PetListModel
import meehan.matthew.petfindr.model.local.PetModel
import meehan.matthew.petfindr.repository.CurrentPetRepository
import meehan.matthew.petfindr.utils.UrlHelper
import javax.inject.Inject

class CurrentPetViewModel @Inject constructor(private val currentPetRepository: CurrentPetRepository) : ViewModel() {

    private var currentPetIndex = 0
    private var nextListLink: String? = null
    private var petList: List<PetModel?> = listOf()

    val currentPet = MutableLiveData<PetModel>()

    init {
        getPets()
    }

    private fun getPets() {
        viewModelScope.launch {
            val response =
                when (nextListLink) {
                    null -> currentPetRepository.getPets()
                    else -> currentPetRepository.getPets(UrlHelper.getPageNumber(nextListLink))
                }

            response?.let {
                nextListLink = it.next
                filterPets(it)
                if (petList.isNotEmpty()) {
                    currentPetIndex = 0
                    currentPet.value = petList.first()
                } else {
                    getPets()
                }
            }
        }
    }

    fun getNextPet() {
        if (currentPetIndex + 1 >= petList.size) {
            currentPetIndex = 0
            getPets()
        } else {
            currentPetIndex++
            currentPet.value = petList[currentPetIndex]
        }
    }

    fun savePet(id: String) {
        val savedPets = currentPetRepository.savedPets
        savedPets?.add(id)
        currentPetRepository.savedPets = savedPets
        getNextPet()
    }

    private fun filterPets(petListModel: PetListModel) {
        petList = petListModel.petList.filter {
             it.photoUrl.isNotEmpty() && it.name.isNotEmpty()
        }
    }
}