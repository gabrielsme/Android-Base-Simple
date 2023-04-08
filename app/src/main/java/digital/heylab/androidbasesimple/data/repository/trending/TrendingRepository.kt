package digital.heylab.androidbasesimple.data.repository.trending

import com.uwetrottmann.tmdb2.Tmdb
import digital.heylab.androidbasesimple.network.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrendingRepository(
    private val tmdb: Tmdb,
    private val responseHandler: ResponseHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

//    suspend fun getTrendingOfWeek() {
//        withContext(dispatcher) {
//            try {
//                tmdb.
//            } catch (e: Exception) {
//
//            }
//        }
//    }
}