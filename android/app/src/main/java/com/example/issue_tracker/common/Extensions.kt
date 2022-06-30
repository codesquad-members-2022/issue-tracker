package com.example.issue_tracker.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.issue_tracker.model.Issue
import com.example.issue_tracker.model.IssueDTOItem
import com.example.issue_tracker.model.Label
import com.example.issue_tracker.network.CEHModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


fun LifecycleOwner.repeatOnLifecycleExtension(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit,
) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(state, block)
    }
}

fun <E> MutableStateFlow<MutableList<E>>.addElement(element: E) {
    if (element == null) {
        return
    }
    val tempMutableList = mutableListOf<E>()
    tempMutableList.addAll(this.value)
    tempMutableList.add(element)
    this.value = tempMutableList
}

fun <E> MutableStateFlow<MutableList<E>>.removeElement(element: E) {
    if (element == null) {
        return
    }
    val tempMutableList = mutableListOf<E>()
    tempMutableList.addAll(this.value)
    tempMutableList.remove(element)
    this.value = tempMutableList
}

fun <E> MutableStateFlow<MutableList<E>>.removeAllElement() {
    val tempMutableList = mutableListOf<E>()
    this.value = tempMutableList
}
