package com.ahmaddudayef.moviesmade.core.util

import android.view.View
import android.widget.ImageView
import com.ahmaddudayef.moviesmade.core.R
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadImageUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context).load(imageUrl).placeholder(R.drawable.ic_download).into(this)
    }
}

fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.showSnackbar(message: String, action: (Snackbar.() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    action?.let { snackbar.it() }
    snackbar.show()
}