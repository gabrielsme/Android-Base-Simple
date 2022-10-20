package digital.heylab.androidbasesimple.data.repository.mapper

import digital.heylab.androidbasesimple.data.repository.model.Movie
import digital.heylab.androidbasesimple.data.source.remote.model.MovieModel

class MovieMapper {
    fun mapper(model: MovieModel) : Movie {
        return Movie(
            title = model.Title
        )
    }
}