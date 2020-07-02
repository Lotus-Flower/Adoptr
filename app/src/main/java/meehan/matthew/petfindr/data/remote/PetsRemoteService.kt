package meehan.matthew.petfindr.data.remote

import meehan.matthew.petfindr.data.remote.api.PetApiService
import meehan.matthew.petfindr.model.local.PetListModel
import meehan.matthew.petfindr.network.NetworkConstants
import meehan.matthew.petfindr.utils.CoroutineUtil
import javax.inject.Inject

class PetsRemoteService @Inject constructor(private val petApiService: PetApiService) {

    suspend fun getData(authToken: String?, page: String?): PetListModel? {
        return PetListModel.convertFromResponse(CoroutineUtil.makeNetworkCall { petApiService.getPets(
            NetworkConstants.BEARER + authToken.orEmpty(), page) })
    }

}