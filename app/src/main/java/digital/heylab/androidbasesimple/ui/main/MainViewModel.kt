package digital.heylab.androidbasesimple.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.heylab.androidbasesimple.data.remote.movies.repository.MoviesRepository
import digital.heylab.androidbasesimple.utils.resource.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _pokemon = MutableSharedFlow<Resource<String>>()
    val pokemon: SharedFlow<Resource<String>> = _pokemon

    fun getPokemon(name: String) {
        viewModelScope.launch {
            _pokemon.emit(Resource.loading())

            moviesRepository.getTopRated().let { pokemon ->
                _pokemon.emit(pokemon)
            }
        }
    }
}