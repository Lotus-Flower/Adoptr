package meehan.matthew.petfindr.dependencyInjection

import com.google.gson.Gson
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Lazy
import meehan.matthew.petfindr.network.NetworkConstants
import meehan.matthew.petfindr.data.remote.PetApiService
import meehan.matthew.petfindr.network.AuthInterceptor
import meehan.matthew.petfindr.repository.PetRepository
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(repository: Lazy<PetRepository>): AuthInterceptor {
        return AuthInterceptor(repository)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(NetworkConstants.PET_BASE_ENDPOINT)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providePetApiService(retrofit: Retrofit) : PetApiService {
        return retrofit.create(PetApiService::class.java)
    }
}