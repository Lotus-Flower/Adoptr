package meehan.matthew.petfindr.repository

import meehan.matthew.petfindr.data.local.AllPetsLocalService
import meehan.matthew.petfindr.data.local.SavedPetsLocalService
import meehan.matthew.petfindr.data.local.TokenLocalService
import meehan.matthew.petfindr.network.NetworkConstants
import meehan.matthew.petfindr.data.remote.PetApiService
import meehan.matthew.petfindr.model.remote.AuthResponse
import meehan.matthew.petfindr.model.remote.PetResponse
import retrofit2.Response
import javax.inject.Inject

class CurrentPetRepository @Inject constructor(private val petApiService: PetApiService,
                                               private val tokenLocalService: TokenLocalService,
                                               private val allPetsLocalService: AllPetsLocalService,
                                               private val savedPetsLocalService: SavedPetsLocalService) {

    var authToken: String?
        get() = tokenLocalService.getData()
        set(value) = tokenLocalService.setData(value)

    var allPets: Set<String>?
        get() = allPetsLocalService.getData()
        set(value) = allPetsLocalService.setData(value)

    var savedPets: Set<String>?
        get() = savedPetsLocalService.getData()
        set(value) = savedPetsLocalService.setData(value)

    suspend fun refreshAuthToken() : Response<AuthResponse> {
        return petApiService.getAuthToken(
            NetworkConstants.GRANT_TYPE_VALUE,
            NetworkConstants.CLIENT_ID_VALUE,
            NetworkConstants.CLIENT_SECRET_VALUE
        )
    }

    suspend fun getPets() : Response<PetResponse> {
        return getPets(null)
    }

    suspend fun getPets(page: String?) : Response<PetResponse> {
        return petApiService.getPets(NetworkConstants.BEARER + authToken.orEmpty(), page)
    }

}