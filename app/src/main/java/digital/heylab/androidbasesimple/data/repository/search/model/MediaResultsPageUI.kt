package digital.heylab.androidbasesimple.data.repository.search.model

class MediaResultsPageUI(
    override val id: Int,
    override var results: List<MediaUI> = listOf()
) : BaseResultsPageUI<MediaUI>()