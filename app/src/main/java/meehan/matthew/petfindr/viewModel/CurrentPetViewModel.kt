package meehan.matthew.petfindr.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import meehan.matthew.petfindr.model.remote.AnimalsItemResponse
import meehan.matthew.petfindr.model.remote.PetResponse
import meehan.matthew.petfindr.repository.PetRepository
import meehan.matthew.petfindr.utils.UrlHelper
import javax.inject.Inject

class CurrentPetViewModel @Inject constructor(private val repository: PetRepository) : ViewModel() {

    val currentPet = MutableLiveData<AnimalsItemResponse?>()
    private var petList: List<AnimalsItemResponse?> = listOf()

    private var currentPetIndex = 0
    private var nextListLink: String? = null

    fun getPets() {
        viewModelScope.launch {
            val response =
                when (nextListLink) {
                    null -> repository.getPets()
                    else -> {
                        repository.getPets(UrlHelper.getPageNumber(nextListLink))
                    }
                }

            if (response.isSuccessful) {
                response.body()?.let {
                    nextListLink = it.pagination?.links?.next?.href
                    filterPets(it)
                    currentPetIndex = 0
                    currentPet.value = petList.firstOrNull()
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

    private fun filterPets(petResponse: PetResponse) {
        petList = petResponse.animals?.filter {
            !it?.photos.isNullOrEmpty() && !it?.name.isNullOrEmpty()
        }.orEmpty()
    }
}