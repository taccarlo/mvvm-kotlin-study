package com.taccarlo.mvvmstudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
/** Making LinkedinRepository parcelable to pass it in a bundle */
@Parcelize
data class LinkedinRepository( val login: String, val id: String, val avatar_url: String, val html_url: String ) :
    Parcelable