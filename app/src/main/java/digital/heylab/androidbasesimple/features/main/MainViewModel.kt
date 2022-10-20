package digital.heylab.androidbasesimple.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.heylab.androidbasesimple.data.repository.movies.MoviesRepository
import digital.heylab.androidbasesimple.data.repository.movies.model.Movie
import digital.heylab.androidbasesimple.utils.resource.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _movie = MutableSharedFlow<Resource<Movie>>()
    val movie: SharedFlow<Resource<Movie>> = _movie

    fun getMovieById(id: String) {
        viewModelScope.launch {
            _movie.emit(Resource.loading())

            moviesRepository.getMovieById(id).let { response ->
                _movie.emit(response)
            }
        }
    }
}