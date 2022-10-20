package digital.heylab.androidbasesimple.data.source.remote.movies

import digital.heylab.androidbasesimple.data.source.remote.movies.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: String): MovieModel
}
