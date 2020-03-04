package meehan.matthew.petfindr.base

import android.app.Application
import meehan.matthew.petfindr.dependencyInjection.AppComponent
import meehan.matthew.petfindr.dependencyInjection.AppModule
import meehan.matthew.petfindr.dependencyInjection.DaggerAppComponent

class BaseApp : Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}