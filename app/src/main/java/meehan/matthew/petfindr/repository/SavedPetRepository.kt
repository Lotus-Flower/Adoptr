package meehan.matthew.petfindr.repository

import meehan.matthew.petfindr.data.local.SavedPetsLocalService
import javax.inject.Inject

class SavedPetRepository @Inject constructor(private val savedPetsLocalService: SavedPetsLocalService) {

    var savedPets: Set<String>?
        get() = savedPetsLocalService.getData()
        set(value) = savedPetsLocalService.setData(value)

}