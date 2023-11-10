package jlmd.dev.android.appandroid.utils

import jlmd.dev.android.appandroid.core.service.gateway.dto.PhotoResponse
import jlmd.dev.android.appandroid.view.model.Photo

fun PhotoResponse.toPhoto() =
    Photo(
        id,
        title,
        url,
        thumbnailUrl
    )