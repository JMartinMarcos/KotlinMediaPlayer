package com.apps.jmm.myplayer

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by sath on 30/11/17.
 */

object MediaProvider {
    private val thumbBase = "http://lorempixel.com/400/400"

    private var mediaItems = emptyList<MediaItem>()

    fun dataAsync(dataType: String, callback: (List<MediaItem>) -> Unit) {
        doAsync {
            if (mediaItems.isEmpty()) {
                mediaItems = dataSync(dataType)
            }
            uiThread { callback(mediaItems) }
        }
    }

    fun dataSync(dataType: String): List<MediaItem> {
        Thread.sleep(2000)
        return (1..10).map {
            MediaItem(it, "Title $it", "$thumbBase/$dataType$it",
                    if (it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO)
        }
    }
}



/*    val mediaItems =  (1..10).map {
        MediaItem("Title $it", "$thumbBase$it", if(it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO)
    }*/


/*fun getMedia() = (1..10).map {
    MediaItem("Title $it", "$thumbBase$it", if(it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO)
        }*/

/*   listOf(     MediaItem("Title 1", "${thumbBase}1", MediaItem.Type.PHOTO),
        MediaItem("Title 2", "${thumbBase}2", MediaItem.Type.PHOTO),
        MediaItem("Title 3", "${thumbBase}3", MediaItem.Type.VIDEO),
        MediaItem("Title 4", "${thumbBase}4", MediaItem.Type.PHOTO),
        MediaItem("Title 5", "${thumbBase}5", MediaItem.Type.PHOTO),
        MediaItem("Title 6", "${thumbBase}6", MediaItem.Type.VIDEO),
        MediaItem("Title 7", "${thumbBase}7", MediaItem.Type.VIDEO),
        MediaItem("Title 8", "${thumbBase}8", MediaItem.Type.PHOTO),
        MediaItem("Title 9", "${thumbBase}9", MediaItem.Type.PHOTO),
        MediaItem("Title 10", "${thumbBase}10", MediaItem.Type.VIDEO)
        )*/
