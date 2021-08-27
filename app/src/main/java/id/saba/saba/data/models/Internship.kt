package id.saba.saba.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Internship(val id: Int, val position: String, val company: Company, val tanggal: String, val description: String) : Parcelable
