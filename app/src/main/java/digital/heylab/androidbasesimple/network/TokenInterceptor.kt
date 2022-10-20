package digital.heylab.androidbasesimple.network

import digital.heylab.androidbasesimple.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.THEMOVIEDB_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)
        return chain.proceed(requestBuilder.build())
    }
}