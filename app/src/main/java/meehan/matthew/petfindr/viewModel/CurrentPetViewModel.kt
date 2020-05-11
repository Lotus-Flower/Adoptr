package meehan.matthew.petfindr.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import meehan.matthew.petfindr.model.remote.AnimalsItemResponse
import meehan.matthew.petfindr.model.remote.PetResponse
import meehan.matthew.petfindr.repository.CurrentPetRepository
import meehan.matthew.petfindr.utils.StringHelper
import meehan.matthew.petfindr.utils.UrlHelper
import javax.inject.Inject

class CurrentPetViewModel @Inject constructor(private val currentPetRepository: CurrentPetRepository) : ViewModel() {

    private val currentPet = MutableLiveData<AnimalsItemResponse?>()
    private var petList: List<AnimalsItemResponse?> = listOf()

    private var currentPetIndex = 0
    private var nextListLink: String? = null

    // region dataBinding fields

    val currentPetPhoto = MediatorLiveData<String?>()

    val currentPetDescription = MediatorLiveData<String?>()

    //endregion dataBinding fields

    init {
        addObservers()
    }

    private fun addObservers() {
        currentPetDescription.addSource(currentPet) {
            it?.let {
                currentPetDescription.value = StringHelper.getPetQuickDescription(it)
            }
        }
        currentPetPhoto.addSource(currentPet) {
            currentPetPhoto.value = it?.photos?.firstOrNull()?.full
        }
    }

    fun getPets() {
        viewModelScope.launch {
            val response =
                when (nextListLink) {
                    null -> currentPetRepository.getPets()
                    else -> currentPetRepository.getPets(UrlHelper.getPageNumber(nextListLink))
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