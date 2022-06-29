package com.example.Beer.viewModel

import androidx.lifecycle.*
import com.example.Beer.model.RecyclerList
import com.example.Beer.services.ApiService
import com.example.Beer.services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityViewModel: ViewModel() {

     lateinit var recyclerLisLiveData : MutableLiveData<RecyclerList>

      init {
        recyclerLisLiveData= MutableLiveData()
    }

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList>{
        return recyclerLisLiveData
    }

    fun makeApiCall(){
        viewModelScope.launch( Dispatchers.IO) {
         val retroInstance = RetrofitInstance.getRtrofitInstance().create(ApiService::class.java)
         val response =  retroInstance.getBeer("2","80")
            recyclerLisLiveData.postValue(response)
        }

    }


}