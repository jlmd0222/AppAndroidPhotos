package jlmd.dev.android.appandroid.core.service.gateway.dto

import com.squareup.moshi.Json

data class PhotoResponse(
    @Json(name = "albumId") val albumId: Int,
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String,
    @Json(name = "thumbnailUrl") val thumbnailUrl: String
)