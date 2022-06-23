package com.moony.routeen.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.entity.Memo
import com.moony.routeen.data.entity.MemoRepository
import com.moony.routeen.data.structure.memo.BaseMemoData
import com.moony.routeen.data.structure.memo.TodoListMemoData
import com.moony.routeen.data.structure.other.DateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MemoRepository):ViewModel() {

    private val _allMemos=MutableLiveData<List<BaseMemoData>>()
    val allMemos:LiveData<List<BaseMemoData>>
        get()=_allMemos

    private val _selectedMemoData=MutableLiveData<BaseMemoData>()
    val selectedMemoData:LiveData<BaseMemoData>
        get()=_selectedMemoData



    fun insertMemo(baseMemoData:BaseMemoData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMemo(Memo(baseMemoData,baseMemoData.date))
        }
    }

    fun getAllMemo(){
        viewModelScope.launch(Dispatchers.IO){
            val data=repository.getAllMemo()
            val response= mutableListOf<BaseMemoData>()

            Log.d("all","memo $response")
            _allMemos.postValue(response)
        }
    }

    fun deleteAllMemoData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMemo()
        }
    }



    fun loadMemoDataByDate(dateFormat: DateFormat){
        viewModelScope.launch(Dispatchers.IO){
            repository.getMemosByDate(dateFormat)
        }
    }

}