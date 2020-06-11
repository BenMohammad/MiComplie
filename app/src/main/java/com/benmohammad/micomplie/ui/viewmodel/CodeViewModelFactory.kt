package com.benmohammad.micomplie.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benmohammad.micomplie.data.CodeRepository

class CodeViewModelFactory(
    private val repository: CodeRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        EditorViewModel(repository) as T


}