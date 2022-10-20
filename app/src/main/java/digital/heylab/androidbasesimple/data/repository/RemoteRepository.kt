package digital.heylab.androidbasesimple.data.repository

import digital.heylab.androidbasesimple.data.repository.mapper.MovieMapper
import digital.heylab.androidbasesimple.data.repository.model.Movie
import digital.heylab.androidbasesimple.data.source.remote.RemoteService
import digital.heylab.androidbasesimple.network.ResponseHandler
import digital.heylab.androidbasesimple.utils.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepository constructor(
    private val remoteService: RemoteService,
    private val responseHandler: ResponseHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getMovieById(id: String): Resource<Movie> {
        return withContext(dispatcher) {
            try {
                val response = remoteService.getMovieById(id)
                val responseMapped = MovieMapper().mapper(response)
                responseHandler.handleSuccess(responseMapped)
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }
}
