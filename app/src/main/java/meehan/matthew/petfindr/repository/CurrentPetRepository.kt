package meehan.matthew.petfindr.repository

import meehan.matthew.petfindr.data.local.AllPetsLocalService
import meehan.matthew.petfindr.data.local.SavedPetsLocalService
import meehan.matthew.petfindr.data.local.TokenLocalService
import meehan.matthew.petfindr.network.NetworkConstants
import meehan.matthew.petfindr.data.remote.PetApiService
import meehan.matthew.petfindr.model.local.PetListModel
import meehan.matthew.petfindr.model.local.PetModel
import meehan.matthew.petfindr.model.remote.AuthResponse
import meehan.matthew.petfindr.model.remote.PetResponse
import meehan.matthew.petfindr.utils.CoroutineUtil
import retrofit2.Response
import javax.inject.Inject

class CurrentPetRepository @Inject constructor(private val petApiService: PetApiService,
                                               private val tokenLocalService: TokenLocalService,
                                               private val allPetsLocalService: AllPetsLocalService,
                                               private val savedPetsLocalService: SavedPetsLocalService) {

    private val authToken: String?
        get() = tokenLocalService.getData()

    var allPets: MutableSet<String>?
        get() = allPetsLocalService.getData()
        set(value) = allPetsLocalService.setData(value)

    var savedPets: MutableSet<String>?
        get() = savedPetsLocalService.getData()
        set(value) = savedPetsLocalService.setData(value)

    suspend fun getPets() : PetListModel? {
        return getPets(null)
    }

    suspend fun getPets(page: String?) : PetListModel? {
        return PetListModel.convertFromResponse(CoroutineUtil.makeNetworkCall { petApiService.getPets(NetworkConstants.BEARER + authToken.orEmpty(), page) })
    }

}