package com.mobiletest.cocktaillover.presentation.cocktail_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiletest.cocktaillover.domain.GetIsCocktailAlcoholicInStringForm
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CocktailDetailsViewModel @Inject constructor(
    private val getIsCocktailAlcoholicInStringForm: GetIsCocktailAlcoholicInStringForm
) : ViewModel() {

    // Region Start - Live Data

    private val _isAlcoholic = MutableLiveData<String>()
    val isAlcoholic: LiveData<String> = _isAlcoholic

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> = _name

    private val _category = MutableLiveData<String?>()
    val category: LiveData<String?> = _category

    private val _recommendedGlassType = MutableLiveData<String?>()
    val recommendedGlassType: LiveData<String?> = _recommendedGlassType

    // Region End - Live Data

    var cocktailData: CocktailWithPictureSource? = null

    init {
        _isAlcoholic.value = ""
    }

    fun onCreateCalled(cocktailData: CocktailWithPictureSource) {
        val isAlcoholic = cocktailData.cocktail.isAlcoholic
        _isAlcoholic.value = getIsCocktailAlcoholicInStringForm(isAlcoholic)
        this.cocktailData = cocktailData

        _name.value = cocktailData.cocktail.name
        _category.value = cocktailData.cocktail.category
        _recommendedGlassType.value = cocktailData.cocktail.recommendedGlassType
    }

}