package com.example.genshincompose.entity

class GcData : ArrayList<GcDataItem>()

data class GcDataItem(

    val objectId: String,
    val url: String,
    val name: String,
    val playtime: String,
    val description: String,
    val detailImgUrl: String,
    val from: String


)