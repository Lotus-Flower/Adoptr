package meehan.matthew.petfindr.repository

import meehan.matthew.petfindr.data.local.SavedPetsLocalService
import meehan.matthew.petfindr.data.local.TokenLocalService
import meehan.matthew.petfindr.data.remote.api.PetApiService
import meehan.matthew.petfindr.model.local.PetModel
import meehan.matthew.petfindr.model.local.SingleAnimalModel
import meehan.matthew.petfindr.network.NetworkConstants
import meehan.matthew.petfindr.utils.CoroutineUtil
import javax.inject.Inject

class SavedPetRepository @Inject constructor(private val petApiService: PetApiService,
                                             private val tokenLocalService: TokenLocalService,
                                             private val savedPetsLocalService: SavedPetsLocalService) {

    private val authToken: String?
        get() = tokenLocalService.getData()

    var savedPets: MutableSet<String>?
        get() = savedPetsLocalService.getData()
        set(value) = savedPetsLocalService.setData(value)

    suspend fun getPetById(id: String?) : PetModel? {
        return SingleAnimalModel.convertFromResponse(CoroutineUtil.makeNetworkCall { petApiService.getPetById(NetworkConstants.BEARER + authToken.orEmpty(), id) }).pet
    }
}