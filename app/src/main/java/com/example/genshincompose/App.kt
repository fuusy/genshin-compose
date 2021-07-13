package com.example.genshincompose

import android.app.Application
import cn.bmob.v3.Bmob

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Bmob.initialize(this, "2e9eeb6893c6be77869a66c332e5e73c");
    }

}