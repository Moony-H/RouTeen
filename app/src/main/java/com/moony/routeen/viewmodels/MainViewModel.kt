package com.moony.routeen.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moony.routeen.data.BusAlarmInfoRepository
import com.moony.routeen.data.MemoDataRepository
import com.moony.routeen.ui.view.memo.Memo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MemoDataRepository):ViewModel() {

    private val _allMemos=MutableLiveData<List<Memo>>()
    val allMemos:LiveData<List<Memo>>
        get()=_allMemos
    fun insertMemo(memo: Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMemoData(memo)
        }
    }

    fun getAllMemo(){
        viewModelScope.launch(Dispatchers.IO){
            val data=repository.getAllMemoData()
            _allMemos.postValue(data)
        }
    }

}