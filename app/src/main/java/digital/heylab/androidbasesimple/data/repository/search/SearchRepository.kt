package digital.heylab.androidbasesimple.data.repository.search

import com.uwetrottmann.tmdb2.Tmdb
import digital.heylab.androidbasesimple.data.repository.search.mapper.MediaResultsPageMapper
import digital.heylab.androidbasesimple.data.repository.search.model.MediaResultsPageUI
import digital.heylab.androidbasesimple.network.ResponseHandler
import digital.heylab.androidbasesimple.utils.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepository(
    private val tmdb: Tmdb,
    private val responseHandler: ResponseHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun multi(query: String): Resource<MediaResultsPageUI> {
        return withContext(dispatcher) {
            try {
                val response = tmdb.searchService()
                    .multi(query, 1, "en-US", "", false)
                    .execute()
                    .body()

                if (response != null) {
                    val responseMapper = MediaResultsPageMapper().map(response)
                    responseHandler.handleSuccess(responseMapper)
                } else {
                    responseHandler.handleException(Throwable())
                }
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }
}