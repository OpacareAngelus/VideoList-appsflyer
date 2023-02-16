package extension

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.addImage(photo: String?) {
    Glide.with(this.context)
        .load(photo)
        .into(this)
}