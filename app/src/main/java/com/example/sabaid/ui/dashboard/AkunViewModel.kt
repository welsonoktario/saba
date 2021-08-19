package com.example.sabaid.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AkunViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is akun Fragment"
    }
    val text: LiveData<String> = _text
}