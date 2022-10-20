package digital.heylab.androidbasesimple.data.repository.movies

import digital.heylab.androidbasesimple.data.repository.movies.mapper.MovieMapper
import digital.heylab.androidbasesimple.data.repository.movies.model.Movie
import digital.heylab.androidbasesimple.data.source.remote.movies.MoviesService
import digital.heylab.androidbasesimple.network.ResponseHandler
import digital.heylab.androidbasesimple.utils.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository constructor(
    private val moviesService: MoviesService,
    private val responseHandler: ResponseHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getMovieById(id: String): Resource<Movie> {
        return withContext(dispatcher) {
            try {
                val response = moviesService.getMovieById(id)
                val responseMapped = MovieMapper().mapper(response)
                responseHandler.handleSuccess(responseMapped)
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }
}
