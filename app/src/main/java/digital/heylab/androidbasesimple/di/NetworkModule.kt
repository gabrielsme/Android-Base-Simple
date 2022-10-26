package digital.heylab.androidbasesimple.di

import com.uwetrottmann.tmdb2.Tmdb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.heylab.androidbasesimple.network.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideTmdb(
        client: OkHttpClient,
        @Named("tmdb-api") apiKey: String
    ): Tmdb = object : Tmdb(apiKey) {
        override fun okHttpClient(): OkHttpClient = client.newBuilder()
            .apply { setOkHttpClientDefaults(this) }
            .build()
    }

    @Provides
    fun provideResponseHandler() : ResponseHandler = ResponseHandler()

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

}