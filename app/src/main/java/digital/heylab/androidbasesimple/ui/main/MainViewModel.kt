package digital.heylab.androidbasesimple.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.heylab.androidbasesimple.data.repository.RemoteRepository
import digital.heylab.androidbasesimple.data.repository.model.Pokemon
import digital.heylab.androidbasesimple.utils.resource.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _pokemon = MutableSharedFlow<Resource<Pokemon>>()
    val pokemon: SharedFlow<Resource<Pokemon>> = _pokemon

    fun getPokemon(name: String) {
        viewModelScope.launch {
            _pokemon.emit(Resource.loading())

            remoteRepository.getPokemon(name).let { pokemon ->
                _pokemon.emit(pokemon)
            }
        }
    }
}