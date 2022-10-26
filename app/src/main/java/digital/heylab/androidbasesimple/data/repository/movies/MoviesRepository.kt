package digital.heylab.androidbasesimple.data.repository.movies

import com.uwetrottmann.tmdb2.Tmdb
import digital.heylab.androidbasesimple.network.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MoviesRepository constructor(
    private val tmdb: Tmdb,
    private val responseHandler: ResponseHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {


}
