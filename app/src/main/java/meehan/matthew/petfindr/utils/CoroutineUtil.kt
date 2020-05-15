package meehan.matthew.petfindr.utils

import retrofit2.Response

object CoroutineUtil {
    suspend fun <T: Any> makeNetworkCall(request: suspend () -> Response<T>) : T? {
        val response = request()
        return when (response.isSuccessful) {
            true -> response.body()
            else -> null
        }
    }
}