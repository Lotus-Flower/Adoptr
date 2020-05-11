package meehan.matthew.petfindr.data.local.sharedPreferences

import android.content.SharedPreferences

abstract class SharedPreferencesSetService(private val sharedPreferences: SharedPreferences, private val key: String?) :
    BaseSharedPreferencesService<Set<String>?> {

    override fun getData(): Set<String>? {
        return sharedPreferences.getStringSet(key, setOf())
    }

    override fun setData(value: Set<String>?) {
        with(sharedPreferences.edit()) {
            this.putStringSet(key, value)
            this.commit()
        }
    }

}