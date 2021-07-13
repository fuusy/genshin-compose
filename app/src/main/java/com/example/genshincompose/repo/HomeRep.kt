package com.example.genshincompose.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import com.example.genshincompose.entity.GcDataItem

class HomeRep {
    private val bmobQuery: BmobQuery<GcDataItem> = BmobQuery()

    companion object {
        private const val TAG = "HomeRep"
    }

    fun queryRoleData(successLiveData: MutableLiveData<List<GcDataItem>>) {

        bmobQuery.findObjects(object : FindListener<GcDataItem>() {
            override fun done(list: MutableList<GcDataItem>?, e: BmobException?) {
                if (e == null) {
                    successLiveData.value = list
                } else {

                }
            }

        })
    }

    fun queryDetailById(id: String?, successLiveData: MutableLiveData<GcDataItem>) {
        Log.d(TAG, "queryDetailById: ")

        bmobQuery.getObject(id,object :QueryListener<GcDataItem>(){
            override fun done(p0: GcDataItem?, p1: BmobException?) {
                if (p1 == null) {
                    successLiveData.value = p0
                }
            }
        })

    }

}