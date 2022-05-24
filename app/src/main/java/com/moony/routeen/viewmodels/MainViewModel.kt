package com.moony.routeen.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.entity.MemoData
import com.moony.routeen.data.entity.MemoDataRepository
import com.moony.routeen.data.structure.Memo
import com.moony.routeen.data.structure.TodoListMemo
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
            repository.insertMemoData(MemoData(memo,MemoType.ToDoListMemo))
        }
    }

    fun getAllMemo(){
        viewModelScope.launch(Dispatchers.IO){
            val data=repository.getAllMemoData()
            val response= mutableListOf<Memo>()

            data.forEach {
                if(it.memoType==MemoType.ToDoListMemo){
                    val temp= it.memo as TodoListMemo
                    Log.d("test", temp.test)
                    response.add(it.memo as TodoListMemo)

                }

            }
            Log.d("all","memo $response")
            _allMemos.postValue(response)
        }
    }

    fun deleteAllMemoData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMemoData()
        }
    }

}