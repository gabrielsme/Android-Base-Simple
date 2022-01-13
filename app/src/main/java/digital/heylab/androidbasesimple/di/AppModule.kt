package digital.heylab.androidbasesimple.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import digital.heylab.androidbasesimple.BuildConfig
import digital.heylab.androidbasesimple.data.repository.RemoteRepository
import digital.heylab.androidbasesimple.data.source.remote.RemoteService
import digital.heylab.androidbasesimple.network.ResponseHandler
import digital.heylab.androidbasesimple.ui.main.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { ResponseHandler() }
    single { provideRetrofit(okHttpClient = get()) }
}

val productsModule = module {
    factory { provideRemoteService(retrofit = get()) }
    factory { RemoteRepository(remoteService = get(), responseHandler = get()) }
    viewModel { MainViewModel(remoteRepository = get()) }
}

fun provideRemoteService(retrofit: Retrofit): RemoteService =
    retrofit.create(RemoteService::class.java)

fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
    OkHttpClient
        .Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()
} else {
    OkHttpClient.Builder().build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://pokeapi.co/api/v2/")
        .client(okHttpClient)
        .build()
}
