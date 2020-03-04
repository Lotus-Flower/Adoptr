package meehan.matthew.petfindr.network

import dagger.Lazy
import kotlinx.coroutines.runBlocking
import meehan.matthew.petfindr.model.remote.AuthResponse
import meehan.matthew.petfindr.repository.PetRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor (private val petRepository: Lazy<PetRepository>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(chain.request())

        when (response.code()) {
            401, 403 -> {
                val authResponse = runBlocking {
                    petRepository.get().refreshAuthToken()
                }

                if (authResponse.isSuccessful) {
                    val token = (authResponse.body() as AuthResponse).accessToken

                    petRepository.get().authToken = token

                    token?.let {

                        val builder = request
                            .newBuilder()
                            .removeHeader(NetworkConstants.AUTHORIZATION_HEADER)
                            .addHeader(NetworkConstants.AUTHORIZATION_HEADER, NetworkConstants.BEARER + token)
                            .method(request.method(), request.body())

                        response.close()
                        response = chain.proceed(builder.build())
                    }
                }
            }
        }

        return response
    }
}