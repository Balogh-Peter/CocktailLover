package com.mobiletest.cocktaillover.presentation.cocktail_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiletest.cocktaillover.domain.GetCocktailsWithPictureSources
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val getCocktailsWithPictureSources: GetCocktailsWithPictureSources
) : ViewModel() {

    // Region Start - Live Data

    private val _cocktailsWithPictureSources = MutableLiveData<List<CocktailWithPictureSource>>()
    val cocktailsWithPictureSources: LiveData<List<CocktailWithPictureSource>> =
        _cocktailsWithPictureSources

    private val _openDetailsScreen = MutableLiveData<Boolean>()
    val openDetailsScreen: LiveData<Boolean> = _openDetailsScreen

    private val _openInstructionsScreen = MutableLiveData<Boolean>()
    val openInstructionsScreen: LiveData<Boolean> = _openInstructionsScreen

    private val _progressBarVisible = MutableLiveData<Boolean>()
    val progressBarVisible: LiveData<Boolean> = _progressBarVisible

    // Region End - Live Data

    lateinit var cocktailData: CocktailWithPictureSource

    init {
        _cocktailsWithPictureSources.value = listOf()
        _openDetailsScreen.value = false
        _openInstructionsScreen.value = false
        _progressBarVisible.value = false
    }

    fun onCreateCalled() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarVisible.postValue(true)
            val pictureSources = getCocktailsWithPictureSources()
            _cocktailsWithPictureSources.postValue(pictureSources)
            _progressBarVisible.postValue(false)
        }

    }

    fun onListItemClick(positionInList: Int) {
        cocktailData = _cocktailsWithPictureSources.value!![positionInList]
        _openDetailsScreen.value = true
        _openDetailsScreen.value = false
    }

    fun onInfoItemClick(positionInList: Int) {
        cocktailData = _cocktailsWithPictureSources.value!![positionInList]
        _openInstructionsScreen.value = true
        _openInstructionsScreen.value = false
    }

}