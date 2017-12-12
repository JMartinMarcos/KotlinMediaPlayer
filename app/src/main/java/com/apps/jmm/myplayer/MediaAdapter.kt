package com.apps.jmm.myplayer

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.apps.jmm.kotlin.myPlayer.R
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlin.properties.Delegates

/**
 * Created by sath on 28/11/17.
 */

typealias Listener = (MediaItem) -> Unit

class MediaAdapter (items: List<MediaItem> = emptyList(), private val listener: Listener)
    : RecyclerView.Adapter<MediaAdapter.ItemViewHolder>() {


    var listItem : List<MediaItem> by Delegates.observable(items){ _, _, _ -> notifyDataSetChanged()}

/*
    interface OnMediaClickListener{
        fun onClick(mediaItem: MediaItem)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val itemViewHolder = parent?.inflate(R.layout.item_layout)
        return ItemViewHolder(itemViewHolder)
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        val item = listItem[position]
        holder?.bind(item)
        holder?.itemView?.setOnClickListener { listener(item) }
    }
    override fun getItemCount(): Int = listItem.size

    class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

/*        val image          = find<ImageView>(R.id.media_thumb)
        val textLow        = find<TextView>(R.id.media_title)
        val videoIndicator = find<ImageView>(R.id.media_video_indicator)*/

        fun bind(item: MediaItem) = with(itemView){
                media_title.text = item.nameText
                media_thumb.loadUrl(item.image)
                media_video_indicator.visibility = when (item.type) {
                    MediaItem.Type.PHOTO -> View.GONE
                    MediaItem.Type.VIDEO -> View.VISIBLE
            }
        }
    }
}
