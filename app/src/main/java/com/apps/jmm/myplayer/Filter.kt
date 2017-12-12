package com.apps.jmm.myplayer

/**
 * Created by sath on 8/12/17.
 */
sealed class Filter {
    object None : Filter()
    class ByType (val type: MediaItem.Type) : Filter()
}