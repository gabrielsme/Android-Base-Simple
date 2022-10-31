package digital.heylab.androidbasesimple.data.repository.search.model

data class MediaUI(
    val id: Int,
    val title: String,
    val mediaType: String,
    val poster: String = "https://image.tmdb.org/t/p/w92/59SVNwLfoMnZPPB6ukW6dlPxAdI.jpg"
)