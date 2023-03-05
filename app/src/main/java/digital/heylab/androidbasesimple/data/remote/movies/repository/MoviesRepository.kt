package digital.heylab.androidbasesimple.data.remote.movies.repository

import com.uwetrottmann.tmdb2.Tmdb
import digital.heylab.androidbasesimple.network.ResponseHandler
import digital.heylab.androidbasesimple.utils.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository constructor(
    private val tmdb: Tmdb,
    private val responseHandler: ResponseHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getTopRated(): Resource<String> {
        return withContext(dispatcher) {
            try {
                val response = tmdb.moviesService().topRated(1, "en-US", "1")
//                val responseMapped = PokemonMapper().mapper(response)
                val a = "1"
                responseHandler.handleSuccess(a)
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }

}
