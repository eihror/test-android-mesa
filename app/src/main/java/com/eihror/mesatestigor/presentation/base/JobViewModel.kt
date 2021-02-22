package com.eihror.mesatestigor.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

open class JobViewModel : ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    protected val TAG_JOB_VM: String by lazy {
        this.javaClass.simpleName
    }

    protected val bgScope = CoroutineScope(Dispatchers.Default)

    override fun onCleared() {
        bgScope.cancel()
        super.onCleared()
    }
}