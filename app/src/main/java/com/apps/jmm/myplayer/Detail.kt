package com.apps.jmm.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.apps.jmm.kotlin.myPlayer.R
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity() {

    companion object {
        val ID = "Detail:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(ID, -1)

        MediaProvider.dataAsync("") { media ->
            media.find { it.id == id }?.let {  (_, title, thumbUrl,type) ->
                supportActionBar?.title = title
                detail_thumb.loadUrl(thumbUrl)
                detail_video_indicator.visibility = when (type) {
                    MediaItem.Type.PHOTO -> View.GONE
                    MediaItem.Type.VIDEO -> View.VISIBLE
                }
            }
        }
    }
}
