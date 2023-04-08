package digital.heylab.androidbasesimple.di

import com.uwetrottmann.tmdb2.Tmdb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.heylab.androidbasesimple.BuildConfig
import digital.heylab.androidbasesimple.data.repository.movies.MoviesRepository
import digital.heylab.androidbasesimple.data.repository.search.SearchRepository
import digital.heylab.androidbasesimple.data.repository.trending.TrendingRepository
import digital.heylab.androidbasesimple.network.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named("tmdb-api")
    fun provideTmdbApiKey(): String = BuildConfig.THEMOVIEDB_KEY

    @Provides
    @Singleton
    fun provideRemoteRepository(
        tmdb: Tmdb,
        responseHandler: ResponseHandler,
        dispatcher: CoroutineDispatcher
    ) = MoviesRepository(tmdb, responseHandler, dispatcher)

    @Provides
    @Singleton
    fun provideSearchRepository(
        tmdb: Tmdb,
        responseHandler: ResponseHandler,
        dispatcher: CoroutineDispatcher
    ) = SearchRepository(tmdb, responseHandler, dispatcher)

    @Provides
    @Singleton
    fun provideTrendingRepository(
        tmdb: Tmdb,
        responseHandler: ResponseHandler,
        dispatcher: CoroutineDispatcher
    ) = TrendingRepository(tmdb, responseHandler, dispatcher)

}