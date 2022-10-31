package digital.heylab.androidbasesimple.data.repository.search.model

abstract class BaseResultsPageUI<T>(
    open val id: Int = 0,
    open var page: Int = 0,
    open var totalPages: Int = 0,
    open var totalResults: Int = 0,
    open var results: List<T> = listOf()
)