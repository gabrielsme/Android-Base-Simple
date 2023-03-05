package digital.heylab.androidbasesimple.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.uwetrottmann.tmdb2.Tmdb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.heylab.androidbasesimple.BuildConfig
import digital.heylab.androidbasesimple.network.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

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