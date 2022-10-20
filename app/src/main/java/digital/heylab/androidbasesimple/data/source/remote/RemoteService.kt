package digital.heylab.androidbasesimple.data.source.remote

import digital.heylab.androidbasesimple.data.source.remote.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: String): MovieModel
}
