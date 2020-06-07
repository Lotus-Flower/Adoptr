package meehan.matthew.petfindr.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import meehan.matthew.petfindr.model.local.PetModel
import meehan.matthew.petfindr.repository.SavedPetRepository
import javax.inject.Inject

class SavedPetsViewModel @Inject constructor(private val savedPetRepository: SavedPetRepository) : ViewModel() {

    val savedPetsList = MutableLiveData<MutableList<PetModel?>>().apply { value = mutableListOf() }

    private var savedPets: MutableSet<String>?
        get() = savedPetRepository.savedPets
        set(value) {
            savedPetRepository.savedPets = value
        }

    fun getPetsByIds() {

        if (!savedPetsList.value.orEmpty().map { it?.id }.containsAll(savedPets.orEmpty())) {

            viewModelScope.launch {
                savedPets?.map {
                    async(Dispatchers.IO) {
                        savedPetsList.value?.add(savedPetRepository.getPetById(it))
                    }
                }?.awaitAll()

                savedPetsList.value = savedPetsList.value?.distinct()?.toMutableList()
            }

        }

    }

    fun removePet(petModel: PetModel?) {
        val updatedList = savedPetsList.value
        updatedList?.remove(petModel)
        savedPetsList.value = updatedList
        savedPets = savedPetsList.value?.mapNotNull { it?.id }?.toMutableSet()
    }
}
