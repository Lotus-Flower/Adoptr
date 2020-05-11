package meehan.matthew.petfindr.dependencyInjection

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import meehan.matthew.petfindr.viewModel.CurrentPetViewModel
import meehan.matthew.petfindr.viewModel.SavedPetsViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrentPetViewModel::class)
    abstract fun bindCurrentPetViewModel(currentPetViewModel: CurrentPetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SavedPetsViewModel::class)
    abstract fun bindSavesPetsViewModel(savedPetsViewModel: SavedPetsViewModel): ViewModel
}