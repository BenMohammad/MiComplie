package com.benmohammad.micomplie.data

import com.benmohammad.micomplie.ui.viewmodel.CodeViewModelFactory

object InjectorUtils {

    private fun getNoteRepository(): CodeRepository {
        return CodeRepository.getInstance()
    }

    fun provideCodeRepository(): CodeViewModelFactory {
        val repository = getNoteRepository()
        return CodeViewModelFactory(repository)
    }
}