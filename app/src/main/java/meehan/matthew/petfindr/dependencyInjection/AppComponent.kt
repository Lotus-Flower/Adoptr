package meehan.matthew.petfindr.dependencyInjection

import dagger.Component
import meehan.matthew.petfindr.view.CurrentPetFragment
import meehan.matthew.petfindr.view.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules=[AppModule::class, NetworkModule::class, LocalModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: CurrentPetFragment)
}