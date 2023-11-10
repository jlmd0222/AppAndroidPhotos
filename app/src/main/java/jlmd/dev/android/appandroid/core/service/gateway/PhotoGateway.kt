package jlmd.dev.android.appandroid.core.service.gateway

import jlmd.dev.android.appandroid.core.service.PhotoApi

class PhotoGateway(
    private val photoApi: PhotoApi
): BaseGateway() {

    suspend fun getPhotos() = getResult {
        executeRequest {
            photoApi.getPhotos()
        }
    }
}