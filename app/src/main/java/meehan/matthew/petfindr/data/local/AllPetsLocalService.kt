package meehan.matthew.petfindr.data.local

import android.content.SharedPreferences
import meehan.matthew.petfindr.data.local.sharedPreferences.SharedPreferencesSetService
import meehan.matthew.petfindr.utils.EnvironmentConstants
import javax.inject.Inject

class AllPetsLocalService @Inject constructor(sharedPreferences: SharedPreferences) : SharedPreferencesSetService(sharedPreferences, EnvironmentConstants.ALL_PETS_KEY)