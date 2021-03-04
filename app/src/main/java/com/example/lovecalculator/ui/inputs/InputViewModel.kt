package com.example.lovecalculator.ui.inputs

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.lovecalculator.data.ResultResponse
import com.example.lovecalculator.repository.ResultRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class InputViewModel @ViewModelInject constructor(

    val repository: ResultRepository
) : ViewModel() {

    var result = MutableLiveData<ResultResponse>()


    private val eventChannel = Channel<MyEvent>()
    val event = eventChannel.receiveAsFlow()

    fun getResults(firstName: String, partnerName: String) = viewModelScope.launch {
        val res = repository.getResults(firstName, partnerName)
        result.postValue(res)
        eventChannel.send(MyEvent.NavigateToResultFragment)

    }


    sealed class MyEvent {
        object NavigateToResultFragment : MyEvent()
    }

}