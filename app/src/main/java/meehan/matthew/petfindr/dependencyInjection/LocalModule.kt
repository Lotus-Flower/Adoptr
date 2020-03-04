package meehan.matthew.petfindr.dependencyInjection

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import meehan.matthew.petfindr.base.EnvironmentConstants

@Module
class LocalModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application):
            SharedPreferences {
        return application.getSharedPreferences(EnvironmentConstants.DEFAULT_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

}