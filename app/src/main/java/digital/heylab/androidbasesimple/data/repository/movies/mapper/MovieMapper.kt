package digital.heylab.androidbasesimple.data.repository.movies.mapper

import digital.heylab.androidbasesimple.data.repository.movies.model.Movie
import digital.heylab.androidbasesimple.data.source.remote.movies.model.MovieModel

class MovieMapper {
    fun mapper(model: MovieModel) : Movie {
        return Movie(
            title = model.title
        )
    }
}