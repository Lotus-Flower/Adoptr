package meehan.matthew.petfindr.utils

import android.net.Uri
import meehan.matthew.petfindr.network.NetworkConstants

object UrlHelper {

    fun getPageNumber(link: String?) : String? {
        return Uri.parse(link).getQueryParameter(NetworkConstants.PAGE)
    }
}