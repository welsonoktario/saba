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
) : Parcelable {
    fun html(): String {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "        <style type=\"text/css\">\n" +
                "            @font-face {\n" +
                "                font-family: Montserrat;\n" +
                "                src: url(\"file:///android_asset/www/fonts/montserrat_regular.ttf\")\n" +
                "            }\n" +
                "            body {\n" +
                "                font-family: 'Montserrat', sans-serif;\n" +
                "                text-size: 0.8rem" +
                "            }\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "       $content" +
                "    </body>\n" +
                "</html>"
    }
}
