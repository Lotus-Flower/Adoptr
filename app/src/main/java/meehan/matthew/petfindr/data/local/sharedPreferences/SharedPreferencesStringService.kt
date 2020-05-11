package meehan.matthew.petfindr.data.local.sharedPreferences

import android.content.SharedPreferences

abstract class SharedPreferencesStringService(private val sharedPreferences: SharedPreferences, private val key: String) :
    BaseSharedPreferencesService<String?> {

    override fun getData() : String? {
        return sharedPreferences.getString(key, "")
    }

    override fun setData(value: String?) {
        with(sharedPreferences.edit()) {
            this.putString(key, value)
            this.commit()
        }
    }

}