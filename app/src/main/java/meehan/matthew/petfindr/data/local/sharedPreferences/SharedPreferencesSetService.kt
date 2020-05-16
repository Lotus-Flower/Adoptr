package meehan.matthew.petfindr.data.local.sharedPreferences

import android.content.SharedPreferences

abstract class SharedPreferencesSetService(private val sharedPreferences: SharedPreferences, private val key: String?) :
    BaseSharedPreferencesService<MutableSet<String>?> {

    override fun getData(): MutableSet<String>? {
        return sharedPreferences.getStringSet(key, setOf())?.toMutableSet()
    }

    override fun setData(value: MutableSet<String>?) {
        with(sharedPreferences.edit()) {
            this.putStringSet(key, value)
            this.commit()
        }
    }

}