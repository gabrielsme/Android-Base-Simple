package digital.heylab.androidbasesimple.di

import com.uwetrottmann.tmdb2.Tmdb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.heylab.androidbasesimple.BuildConfig
import digital.heylab.androidbasesimple.data.remote.movies.repository.MoviesRepository
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
    fun provideMoviesRepository(
        tmdb: Tmdb,
        responseHandler: ResponseHandler,
        dispatcher: CoroutineDispatcher
    ) = MoviesRepository(tmdb, responseHandler, dispatcher)
}