package meehan.matthew.petfindr.data.remote

import meehan.matthew.petfindr.model.remote.PetResponse
import meehan.matthew.petfindr.model.remote.AuthResponse
import meehan.matthew.petfindr.network.NetworkConstants
import retrofit2.Response
import retrofit2.http.*

interface PetApiService {

    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun getAuthToken(@Field(NetworkConstants.GRANT_TYPE_KEY) grantType: String,
                             @Field(NetworkConstants.CLIENT_ID_KEY) clientId: String,
                             @Field(NetworkConstants.CLIENT_SECRET_KEY) clientSecret: String)
            : Response<AuthResponse>

    @GET("animals")
    suspend fun getPets(@Header(NetworkConstants.AUTHORIZATION_HEADER) token: String, @Query(NetworkConstants.PAGE) page: String?) : Response<PetResponse>

}