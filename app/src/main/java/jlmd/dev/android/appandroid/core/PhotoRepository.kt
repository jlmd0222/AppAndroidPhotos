package jlmd.dev.android.appandroid.core

import jlmd.dev.android.appandroid.core.service.gateway.PhotoGateway
import jlmd.dev.android.appandroid.utils.RequestResult
import jlmd.dev.android.appandroid.utils.thenSuspending
import jlmd.dev.android.appandroid.utils.toPhoto
import jlmd.dev.android.appandroid.view.model.Photo

class PhotoRepository(
    private val photoGateway: PhotoGateway
) {

    suspend fun getPhotos(): RequestResult<List<Photo>> {
        return photoGateway.getPhotos()
            .thenSuspending { photosResponse ->
                val photos = photosResponse.orEmpty()
                    .take(15).toList()
                    .map { it.toPhoto() }

                RequestResult.Success(photos)
            }
    }
}