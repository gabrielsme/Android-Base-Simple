package digital.heylab.androidbasesimple.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.heylab.androidbasesimple.data.repository.movies.MoviesRepository
import digital.heylab.androidbasesimple.data.source.remote.movies.MoviesService
import digital.heylab.androidbasesimple.network.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(
        moviesService: MoviesService,
        responseHandler: ResponseHandler,
        dispatcher: CoroutineDispatcher
    ) = MoviesRepository(moviesService, responseHandler, dispatcher)
}