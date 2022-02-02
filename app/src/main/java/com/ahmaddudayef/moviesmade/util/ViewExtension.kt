package com.ahmaddudayef.moviesmade.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.ahmaddudayef.moviesmade.R
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadImageUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(this)
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

fun Context.showSnackbar(view: View, message: String): Snackbar {
    return Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
        show()
    }
}