package jlmd.dev.android.appandroid.core.service

import jlmd.dev.android.appandroid.core.service.gateway.dto.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET

interface PhotoApi {

    @GET("/photos")
    suspend fun getPhotos(): Response<List<PhotoResponse>>
}