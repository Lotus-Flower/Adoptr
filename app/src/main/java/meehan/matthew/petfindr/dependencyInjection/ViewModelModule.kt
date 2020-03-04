package meehan.matthew.petfindr.dependencyInjection

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import meehan.matthew.petfindr.viewModel.CurrentPetViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrentPetViewModel::class)
    abstract fun bindCurrentPetViewModel(currentPetViewModel: CurrentPetViewModel): ViewModel
}