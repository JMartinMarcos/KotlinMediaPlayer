package com.apps.jmm.myplayer

/**
 * Created by sath on 28/11/17.
 */
data class MediaItem(var id: Int, var nameText: String, var image:String, val type: Type){
    enum class Type { PHOTO, VIDEO }
    }
