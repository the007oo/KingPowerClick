package com.phattarapong.kingpowerclick.network

import com.phattarapong.kingpowerclick.model.PhotoRemoteModel
import io.reactivex.Single
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


private val service: Service by lazy {
    val okHttpClient = OkHttpClient.Builder().apply {
        readTimeout(360,TimeUnit.SECONDS)
        writeTimeout(360,TimeUnit.SECONDS)
        connectTimeout(360,TimeUnit.SECONDS)

        addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        addInterceptor(Interceptor {
            val original = it.request()
            val body: RequestBody = FormBody.Builder().build()
            val request = original.newBuilder()
//                .addHeader(
//                    "Authorization",
//                    AppPreference(Contextor.context!!).getValueString(ACCESS_TOKEN, "")!!
//                )
                .post(body)
                .method(original.method, original.body)
                .build()

            val response = it?.proceed(request)

            return@Interceptor response
        })
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(okHttpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    retrofit.create(Service::class.java)
}

fun getNetworkService() = service

/**
 * Main network interface which will fetch a new welcome title for us
 */
interface Service {
    @GET("albums/1/photos")
    fun getPhotos():  Single<List<PhotoRemoteModel>>
}