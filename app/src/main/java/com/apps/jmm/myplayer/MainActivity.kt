package com.apps.jmm.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.apps.jmm.kotlin.myPlayer.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

/*
    var adapter = MediaAdapter { (nameText,_,_) -> toast(nameText)}
*/
    var adapter = MediaAdapter { (id) -> navigateToDetail(id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.adapter = adapter
        loadFilteredData(Filter.None)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val filter: Filter = when(item.itemId){
            R.id.filter_photos -> Filter.ByType(MediaItem.Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(MediaItem.Type.VIDEO)
            else -> Filter.None
        }

         loadFilteredData(filter)

/*        MediaProvider.dataAsync { media ->
            adapter.listItem = when (item.itemId) {
                R.id.filter_all -> media.sortedBy { it.nameText }++

                R.id.filter_photos -> media.filter { it.type == MediaItem.Type.PHOTO }.sortedBy { it.nameText }
                R.id.filter_videos -> media.filter { it.type == MediaItem.Type.VIDEO }.sortedBy { it.nameText }
                else -> media.sortedBy { it.nameText }
            }
        }*/
            return true
    }

    private fun loadFilteredData(filter: Filter) {
/*        MediaProvider.dataAsync { media ->
            adapter.listItem = when (filter) {
                Filter.None      -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }*/

        async(UI){
            val cats = bg{ MediaProvider.dataSync("cats")}
            val nature = bg{ MediaProvider.dataSync("nature")}
            adapter.listItem = cats.await() + nature.await()
        }
    }

    private fun navigateToDetail(id: Int) {
        startActivity<Detail>(Detail.ID to id)
    }
}
