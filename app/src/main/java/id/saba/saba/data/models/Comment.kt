package id.saba.saba.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Comment(
    val id: Int,
    val user: String,
    val tanggal: String,
    val comment: String,
    val upvote: Int,
    val downvote: Int
) : Parcelable {
    fun postedTimeDiff(): String {
        val SECOND_MILLIS = 1000
        val MINUTE_MILLIS = 60 * SECOND_MILLIS
        val HOUR_MILLIS = 60 * MINUTE_MILLIS
        val DAY_MILLIS = 24 * HOUR_MILLIS

        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val posted = formatter.parse(tanggal)
        val currentDate = Date()
        val diff = posted!!.time - currentDate.time

        return when {
            diff < MINUTE_MILLIS -> "just now"
            diff < 2 * MINUTE_MILLIS -> "a minute ago"
            diff < 60 * MINUTE_MILLIS -> (diff / MINUTE_MILLIS).toString() + " minutes ago"
            diff < 120 * MINUTE_MILLIS -> "an hour ago"
            diff < 24 * HOUR_MILLIS -> (diff / HOUR_MILLIS).toString() + " hours ago"
            diff < 48 * HOUR_MILLIS -> "yesterday"
            else -> (diff / DAY_MILLIS).toString() + " days ago"
        }
    }
}
