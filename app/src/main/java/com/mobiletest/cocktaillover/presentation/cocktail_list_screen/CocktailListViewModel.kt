package com.mobiletest.cocktaillover.presentation.cocktail_list_screen

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

    var cocktailsWithPictureSources = MutableLiveData<List<CocktailWithPictureSource>>()

    init {
        cocktailsWithPictureSources.value = listOf()
    }

    fun onViewCreatedCalled() {
        viewModelScope.launch(Dispatchers.IO) {
            val pictureSources = getCocktailsWithPictureSources()
            cocktailsWithPictureSources.postValue(pictureSources)
        }
    }

    fun onListItemClick(positionInList: Int) {

    }


}