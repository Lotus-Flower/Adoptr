package meehan.matthew.petfindr.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

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