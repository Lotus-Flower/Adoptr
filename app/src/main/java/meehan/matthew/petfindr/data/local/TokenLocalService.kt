package meehan.matthew.petfindr.data.local

import android.content.SharedPreferences
import meehan.matthew.petfindr.base.EnvironmentConstants
import javax.inject.Inject

class TokenLocalService @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var authToken : String?
        get() {
            return sharedPreferences.getString(EnvironmentConstants.AUTH_TOKEN_KEY, "")
        }
        set(value) {
            with(sharedPreferences.edit()) {
                this.putString(EnvironmentConstants.AUTH_TOKEN_KEY, value)
                this.commit()
            }
        }

}