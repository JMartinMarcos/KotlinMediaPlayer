package com.apps.jmm.myplayer

/*interface Callback{
        fun onCallback(result: String)
}

fun doAsync(x: Int, callback: Callback){
    callback.onCallback("finished")
}

fun test() {
    doAsync(20, object : Callback {
        override fun onCallback(result: String) {
            print(result)
        }
    })
}*/

fun doAsync(x: Int, s: String, f:(String) -> Unit) = f(s)

fun clicker(mediaItem: MediaItem, f: (MediaItem)-> Unit) = f(mediaItem)

fun test(){
    doAsync(20, "finished") { result -> print(result) }
}
