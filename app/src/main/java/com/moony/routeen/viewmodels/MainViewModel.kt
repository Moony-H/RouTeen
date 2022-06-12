package com.moony.routeen.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moony.routeen.data.MemoType
import com.moony.routeen.data.entity.Memo
import com.moony.routeen.data.entity.MemoRepository
import com.moony.routeen.data.structure.memo.TodoListMemoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MemoRepository):ViewModel() {

    private val _allMemos=MutableLiveData<List<com.moony.routeen.data.structure.memo.BaseMemoData>>()
    val allMemos:LiveData<List<com.moony.routeen.data.structure.memo.BaseMemoData>>
        get()=_allMemos
    fun insertMemo(baseMemoData: com.moony.routeen.data.structure.memo.BaseMemoData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMemo(Memo(baseMemoData,MemoType.TodoListMemo))
        }
    }

    fun getAllMemo(){
        viewModelScope.launch(Dispatchers.IO){
            val data=repository.getAllMemo()
            val response= mutableListOf<com.moony.routeen.data.structure.memo.BaseMemoData>()

            data.forEach {
                if(it.memoType==MemoType.TodoListMemo){
                    val temp= it.baseMemoData as TodoListMemoData
                    Log.d("test", "func call ${temp.memoType}")
                    response.add(it.baseMemoData as TodoListMemoData)

                }

            }
            Log.d("all","memo $response")
            _allMemos.postValue(response)
        }
    }

    fun deleteAllMemoData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMemo()
        }
    }

}