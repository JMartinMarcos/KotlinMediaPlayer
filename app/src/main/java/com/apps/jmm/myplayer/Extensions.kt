package com.apps.jmm.myplayer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Context.toast(message: String, timeShowing: Int = Toast.LENGTH_LONG) = Toast.makeText(this,message,timeShowing).show()

fun RecyclerView.ViewHolder.toast(message: String, timeShowing: Int = Toast.LENGTH_LONG) = itemView.context.toast(message,timeShowing)

fun ViewGroup.inflate(itemHolder:Int): View = LayoutInflater.from(context).inflate(itemHolder,this,false)

fun ImageView.loadUrl(url:String) = Glide.with(context).load(url).into(this)

inline fun <reified T: View> View.find(idRes: Int):T =findViewById<T>(idRes)

inline fun <reified T: View> RecyclerView.ViewHolder.find(idRes: Int):T = itemView.find(idRes)

