package zk.bnb.android.sdk.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Helper class to create network related classes.
 * Constructs instance of OkhttpClient and Retrofit
 *
 * @property networkConfig
 */
class NetworkFactory(
    private val networkConfig: NetworkConfig
) {

    val okHttpClient: OkHttpClient by lazy {
        return@lazy createOkHttpClient()
    }

    val retrofitApi: Retrofit by lazy {
        return@lazy createRetrofit()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            followRedirects(true)
            followSslRedirects(true)
            connectTimeout(networkConfig.timeout, TimeUnit.SECONDS)
            writeTimeout(networkConfig.timeout, TimeUnit.SECONDS)
            readTimeout(networkConfig.timeout, TimeUnit.SECONDS)
            if (networkConfig.isDebug) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }
        return okHttpClientBuilder.build()
    }

    private fun createRetrofit(): Retrofit {
        val converterFactory = GsonConverterFactory.create(networkConfig.gson)
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .baseUrl(networkConfig.baseApiUrl)
            .client(okHttpClient)
            .build()
    }
}