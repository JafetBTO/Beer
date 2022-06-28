package com.example.Beer.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.Beer.model.BeerDataModel
import com.example.Beer.repository.BeerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecyclerBeerViewModel(app: Application): AndroidViewModel(app),CoroutineScope {

    private val _itemSelected = MutableLiveData<BeerDataModel>()
    var itemDataSelected: BeerDataModel? = null

    private val _listState = MutableLiveData<MutableList<BeerDataModel>>()
    var listState: LiveData<MutableList<BeerDataModel>> = _listState

    private val _progressState = MutableLiveData<Boolean>()
    var progressState: LiveData<Boolean> = _progressState

    private val repository = BeerRepository()
    lateinit var observerOnCategorySelected: Observer<BeerDataModel>

    private val viewModelJob = Job()
    override val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Default

    init {
        initObserver()
    }

    private fun initObserver() {
        observerOnCategorySelected = Observer { value ->
            value.let {
                _itemSelected.value = it
            }
        }
    }

    /*
    fun clearSelection() {
        _itemSelected.value = null
    }
     */

    fun setItemSelection(item: BeerDataModel) {
        itemDataSelected = item
    }

    fun fetchBeerData() {
        _progressState.value = true
        viewModelScope.launch {
            val response = repository.getBeer()
            response?.body()?.beer.let { list->
                _listState.value = list
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    // Memory leak

}