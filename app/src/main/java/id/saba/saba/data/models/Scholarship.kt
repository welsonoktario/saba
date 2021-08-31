package id.saba.saba.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Scholarship(
    val id: Int,
    val company: Company,
    val thumbnail: String,
    val judul: String,
    val kutipan: String,
    val content: String
) :
    Parcelable
