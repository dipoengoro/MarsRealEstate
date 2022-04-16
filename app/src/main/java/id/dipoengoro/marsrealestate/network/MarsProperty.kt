package id.dipoengoro.marsrealestate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsProperty(
    val id: String,
    @Json(name = "img_src")
    val imgSrcUrl: String,
    val price: Double,
    val type: String
) : Parcelable {
    val isRental
        get() = type == "rent"
}