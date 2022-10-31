package digital.heylab.androidbasesimple.features.main.search

import digital.heylab.androidbasesimple.data.repository.search.model.MediaResultsPageUI

data class SearchViewState(
    val query: String = "",
    val searchResult: MediaResultsPageUI? = null,
    val isLoading: Boolean = false
) {
    companion object {
        val Empty = SearchViewState()
    }
}