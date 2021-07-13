package com.example.genshincompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.genshincompose.entity.GcDataItem
import com.example.genshincompose.base.LiveCoroutinesViewModel
import com.example.genshincompose.repo.HomeRep

class HomeViewModel : LiveCoroutinesViewModel() {

    private val repo: HomeRep = HomeRep()

    private val _gcListLiveData = MutableLiveData<List<GcDataItem>>()
    private val _gcDetailLiveData = MutableLiveData<GcDataItem>()

    fun getDetailLiveData(): LiveData<GcDataItem> {
        return _gcDetailLiveData
    }

    fun getDataLiveData(): LiveData<List<GcDataItem>> {
        return _gcListLiveData
    }

    fun queryGcData() {
        repo.queryRoleData(_gcListLiveData)
    }

    fun queryGcDetailById(objectId: String?) {
        repo.queryDetailById(objectId, _gcDetailLiveData)
    }


}