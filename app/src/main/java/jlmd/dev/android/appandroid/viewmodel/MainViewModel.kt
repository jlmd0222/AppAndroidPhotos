package jlmd.dev.android.appandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jlmd.dev.android.appandroid.core.usecases.GetPhotosUseCase
import jlmd.dev.android.appandroid.view.model.Photo
import jlmd.dev.android.appandroid.utils.RequestResult
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPhotosUseCase: GetPhotosUseCase
): ViewModel() {

    private val _photosViewState = MutableLiveData<PhotosViewState>()
    val photosViewState: LiveData<PhotosViewState> get() = _photosViewState

    init {
        getPhotos()
    }

    fun getPhotos() {
        _photosViewState.value = PhotosViewState.Loading

        viewModelScope.launch {
            val photos = getPhotosUseCase.invoke()

            _photosViewState.value = when (photos) {
                is RequestResult.Success -> PhotosViewState.ShowPhotos(photos.data)
                is RequestResult.Error -> PhotosViewState.Error
            }
        }
    }
}

sealed class PhotosViewState {
    object Loading: PhotosViewState()
    data class ShowPhotos(val photos: List<Photo>): PhotosViewState()
    object Error: PhotosViewState()
}