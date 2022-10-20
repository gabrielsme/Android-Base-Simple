package digital.heylab.androidbasesimple.data.source.remote

import digital.heylab.androidbasesimple.data.source.remote.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("/?")
    suspend fun getMovieById(@Query("i") i: String): MovieModel
}
