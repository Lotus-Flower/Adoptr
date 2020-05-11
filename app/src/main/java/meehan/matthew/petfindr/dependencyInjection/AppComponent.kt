package meehan.matthew.petfindr.dependencyInjection

import dagger.Component
import meehan.matthew.petfindr.view.CurrentPetFragment
import meehan.matthew.petfindr.view.MainActivity
import meehan.matthew.petfindr.view.SavedPetsFragment
import javax.inject.Singleton

@Singleton
@Component(modules=[AppModule::class, NetworkModule::class, LocalModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: CurrentPetFragment)
    fun inject(fragment: SavedPetsFragment)
}