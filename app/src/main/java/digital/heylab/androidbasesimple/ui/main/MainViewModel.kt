package digital.heylab.androidbasesimple.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import digital.heylab.androidbasesimple.data.repository.RemoteRepository
import digital.heylab.androidbasesimple.data.repository.model.Pokemon
import digital.heylab.androidbasesimple.utils.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _pokemon = MutableLiveData<Resource<Pokemon>>()
    val pokemon: LiveData<Resource<Pokemon>>
        get() = _pokemon

    fun getPokemon(name: String) {
        _pokemon.value = Resource.loading(null)

        viewModelScope.launch {
            remoteRepository.getPokemon(name).let { pokemon ->
                _pokemon.postValue(Resource.success(pokemon))
            }
        }
    }
}