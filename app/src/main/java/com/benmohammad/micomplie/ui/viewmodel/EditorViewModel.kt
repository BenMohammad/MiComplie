package com.benmohammad.micomplie.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.benmohammad.micomplie.data.CodeRepository

class EditorViewModel(
    private val repository: CodeRepository
): ViewModel() {

    fun getCode(): String {
        return repository.getCode()
    }

    fun setCode(text: String) {
        repository.saveCode(text)
    }
}