package meehan.matthew.petfindr.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

object CustomBindings {

    @JvmStatic
    @BindingAdapter("remoteImageUrl")
    fun bindingAdapter(imageView: ImageView, url: String?) {
        Picasso
            .get()
            .load(url)
            .into(imageView)
    }

}