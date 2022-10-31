package digital.heylab.androidbasesimple.features.main.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import digital.heylab.androidbasesimple.R
import digital.heylab.androidbasesimple.data.repository.search.model.MediaUI
import digital.heylab.androidbasesimple.ui.components.SearchTextField

@Composable
fun SearchScreen() {
    Search(
        viewModel = hiltViewModel()
    )
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun Search(
    viewModel: SearchViewModel
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

    Search(viewState, viewModel::search)
}

@Composable
fun Search(
    state: SearchViewState,
    onSearchQueryChanged: (query: String) -> Unit,
) {
    Scaffold(
        topBar = {
            Surface(
                color = MaterialTheme.colors.surface.copy(alpha = 0.95f),
                contentColor = MaterialTheme.colors.onSurface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .statusBarsPadding()
                        .fillMaxWidth()
                ) {
                    var searchQuery by remember { mutableStateOf(TextFieldValue(state.query)) }
                    SearchTextField(
                        value = searchQuery,
                        onValueChange = { value ->
                            searchQuery = value
                            onSearchQueryChanged(value.text)
                        },
                        hint = stringResource(id = R.string.search),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    ) { padding ->
        SearchList(
            results = state.searchResult?.results ?: listOf(),
            contentPadding = padding,
            onShowClicked = { }, //openShowDetails(it.id) },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchList(
    results: List<MediaUI>,
    onShowClicked: (MediaUI) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(
            items = results,
            itemContent = { item ->
                SearchRow(
                    show = item,
                    modifier = Modifier
                        .animateItemPlacement()
                        .fillMaxWidth()
                        .clickable { onShowClicked(item) }
                )
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchRow(
    @PreviewParameter(MediaUIPreviewProvider::class) show: MediaUI,
    modifier: Modifier = Modifier
) {
    Column {

        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            PosterCard(
                show = show,
                modifier = Modifier
                    .fillMaxWidth(0.2f) // 20% of width
                    .aspectRatio(2 / 3f)
            )

            Spacer(Modifier.width(8.dp))

            Column(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = show.title,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }

        Divider(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PosterCard(
    @PreviewParameter(MediaUIPreviewProvider::class) show: MediaUI,
    modifier: Modifier = Modifier,
//    poster: TmdbImageEntity? = null,
//    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
    ) {
        Box(
//            modifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
        ) {
            // TODO: remove text if the image has loaded (and animated in).
            // https://github.com/chrisbanes/accompanist/issues/76
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = show.title ,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterStart)
                )
            }
            AsyncImage(
                model = show.poster,
//                requestBuilder = { crossfade(true) },
                contentDescription = stringResource(
                    R.string.cd_show_poster_image,
                    show.title
                ),
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

class MediaUIPreviewProvider : PreviewParameterProvider<MediaUI> {
    override val values = sequenceOf(
        MediaUI(
            id = 1,
            title = "Harry Potter",
            mediaType = "movie"
        )
    )
}