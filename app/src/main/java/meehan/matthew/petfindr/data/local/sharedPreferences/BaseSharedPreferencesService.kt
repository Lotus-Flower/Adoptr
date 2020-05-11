package meehan.matthew.petfindr.data.local.sharedPreferences

interface BaseSharedPreferencesService<T> {

    fun getData() : T

    fun setData(value: T)

}