package com.assesment.base.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    val dataLoading = ObservableBoolean(false)

    private val _showMessage = MutableSharedFlow<String>()
    val showMessage = _showMessage.asSharedFlow()

    fun emitMessage(message: String?) {
        viewModelScope.launch(Dispatchers.Default) {
            message?.let {
                _showMessage.emit(it)
            }
        }
    }
}
