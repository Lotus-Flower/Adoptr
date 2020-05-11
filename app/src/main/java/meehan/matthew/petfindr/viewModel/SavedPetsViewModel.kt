package meehan.matthew.petfindr.viewModel

import androidx.lifecycle.ViewModel
import meehan.matthew.petfindr.repository.SavedPetRepository
import javax.inject.Inject

class SavedPetsViewModel @Inject constructor(private val savedPetRepository: SavedPetRepository) : ViewModel() {
    
    var savedPets: Set<String>?
        get() = savedPetRepository.savedPets
        set(value) {
            savedPetRepository.savedPets = value
        }

}