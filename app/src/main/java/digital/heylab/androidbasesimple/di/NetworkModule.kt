package digital.heylab.androidbasesimple.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.heylab.androidbasesimple.BuildConfig
import digital.heylab.androidbasesimple.data.source.remote.RemoteService
import digital.heylab.androidbasesimple.network.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Provides
    fun provideRemoteService(retrofit: Retrofit): RemoteService =
        retrofit.create(RemoteService::class.java)

    @Provides
    fun provideResponseHandler() : ResponseHandler = ResponseHandler()

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

}