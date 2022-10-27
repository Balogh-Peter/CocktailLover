package com.mobiletest.cocktaillover.presentation.instructions_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InstructionsViewModel @Inject constructor() : ViewModel() {

    // Region Start - Live Data

    private val _instructions = MutableLiveData<String>()
    val instructions: LiveData<String> = _instructions

    // Region End - Live Data

    var cocktailData: CocktailWithPictureSource? = null


    fun onCreateCalled(cocktailData: CocktailWithPictureSource) {
        _instructions.value = cocktailData.cocktail.instructions
        this.cocktailData = cocktailData
    }

}