package digital.heylab.androidbasesimple.data.repository.search.mapper

import com.uwetrottmann.tmdb2.entities.Media
import com.uwetrottmann.tmdb2.entities.MediaResultsPage
import com.uwetrottmann.tmdb2.enumerations.MediaType
import digital.heylab.androidbasesimple.base.Mapper
import digital.heylab.androidbasesimple.data.repository.search.model.MediaResultsPageUI
import digital.heylab.androidbasesimple.data.repository.search.model.MediaUI

class MediaResultsPageMapper : Mapper<MediaResultsPageUI, MediaResultsPage> {
    override fun map(model: MediaResultsPage): MediaResultsPageUI {
        return MediaResultsPageUI(
            id = model.id ?: 0,
            results = model.results?.map { item -> mapMultiItem(item) } ?: listOf()
        )
    }

    private fun mapMultiItem(model: Media): MediaUI {
        return when (model.media_type) {
            MediaType.MOVIE -> mapMovie(model)
            MediaType.TV -> mapTvShow(model)
            MediaType.PERSON -> mapPerson(model)
        }
    }

    private fun mapMovie(model: Media): MediaUI {
        return MediaUI(
            id = model.movie?.id ?: 0,
            title = model.movie?.title ?: "",
            mediaType = model.media_type?.name ?: "",
        )
    }

    private fun mapTvShow(model: Media): MediaUI {
        return MediaUI(
            id = model.tvShow?.id ?: 0,
            title = model.tvShow?.name ?: "",
            mediaType = model.media_type?.name ?: "",
        )
    }

    private fun mapPerson(model: Media): MediaUI {
        return MediaUI(
            id = model.person?.id ?: 0,
            title = model.person?.name ?: "",
            mediaType = model.media_type?.name ?: "",
        )
    }
}