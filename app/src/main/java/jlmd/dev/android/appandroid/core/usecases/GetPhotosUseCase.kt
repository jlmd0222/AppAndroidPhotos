package jlmd.dev.android.appandroid.core.usecases

import jlmd.dev.android.appandroid.view.model.Photo
import jlmd.dev.android.appandroid.utils.RequestResult
import jlmd.dev.android.appandroid.core.PhotoRepository

class GetPhotosUseCase(
    private val photoRepository: PhotoRepository
) {

    suspend fun invoke(): RequestResult<List<Photo>> {
        return photoRepository.getPhotos()
    }
}