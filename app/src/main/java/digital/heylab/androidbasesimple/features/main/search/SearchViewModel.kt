package digital.heylab.androidbasesimple.features.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.heylab.androidbasesimple.data.repository.search.SearchRepository
import digital.heylab.androidbasesimple.data.repository.search.model.MediaResultsPageUI
import digital.heylab.androidbasesimple.utils.resource.Resource
import digital.heylab.androidbasesimple.utils.resource.Status
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    private val _state: MutableStateFlow<SearchViewState> by lazy {
        MutableStateFlow(SearchViewState.Empty)
    }
    val state: StateFlow<SearchViewState> = _state

    init {
        viewModelScope.launch {
            searchQuery.debounce(300)
                .onEach { query ->
                    val job = launch {
                        _state.update { it.copy(isLoading = true) }
                        val result = searchRepository.multi(query)
                        update(result)
                    }
                     job.invokeOnCompletion {  } // loading finish
                    job.join()
                }
                .catch {  }
                .collect()
        }

        search("potter")
    }

    private fun update(result: Resource<MediaResultsPageUI>) {
        when (result.status) {
            Status.ERROR -> {
                _state.update { it.copy(isLoading = false) }
            }
            Status.SUCCESS -> {
                _state.update { it.copy(
                    isLoading = false,
                    searchResult = result.data
                ) }
            }
            Status.LOADING -> {
                _state.update { it.copy(isLoading = true) }
            }
        }
    }

    fun search(searchTerm: String) {
        searchQuery.value = searchTerm
    }
}