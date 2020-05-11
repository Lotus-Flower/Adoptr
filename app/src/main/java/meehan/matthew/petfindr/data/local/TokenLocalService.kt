package meehan.matthew.petfindr.data.local

import android.content.SharedPreferences
import meehan.matthew.petfindr.data.local.sharedPreferences.SharedPreferencesStringService
import meehan.matthew.petfindr.utils.EnvironmentConstants
import javax.inject.Inject

class TokenLocalService @Inject constructor(sharedPreferences: SharedPreferences) : SharedPreferencesStringService(sharedPreferences, EnvironmentConstants.AUTH_TOKEN_KEY)