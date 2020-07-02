package meehan.matthew.petfindr.repository

import meehan.matthew.petfindr.data.local.TokenLocalService
import meehan.matthew.petfindr.data.remote.api.PetApiService
import meehan.matthew.petfindr.model.remote.AuthResponse
import meehan.matthew.petfindr.network.NetworkConstants
import meehan.matthew.petfindr.utils.CoroutineUtil
import javax.inject.Inject

class TokenRepository @Inject constructor(private val petApiService: PetApiService,
                                          private val tokenLocalService: TokenLocalService) {

    var authToken: String?
        get() = tokenLocalService.getData()
        set(value) = tokenLocalService.setData(value)

    suspend fun refreshAuthToken() : AuthResponse? {
        return CoroutineUtil.makeNetworkCall {
            petApiService.getAuthToken(
                NetworkConstants.GRANT_TYPE_VALUE,
                NetworkConstants.CLIENT_ID_VALUE,
                NetworkConstants.CLIENT_SECRET_VALUE
            )
        }
    }
}