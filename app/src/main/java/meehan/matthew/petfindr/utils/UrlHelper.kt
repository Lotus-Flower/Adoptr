package meehan.matthew.petfindr.utils

import android.net.Uri
import android.net.UrlQuerySanitizer


object UrlHelper {

    fun getPageNumber(link: String?) : String? {
        return Uri.parse(link).getQueryParameter("page")
    }
}