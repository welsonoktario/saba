package id.saba.saba.data.models

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Forum(
    val id: Int,
    val headline: String,
    val deskripsi: String,
    val tanggal: String,
    val user: User,
    val upvote: Int,
    val downvote: Int,
    val viewer: Int,
    val comments: ArrayList<Comment>
) {
    fun userUsername() = user.username

    fun commentCount() = comments.size

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
