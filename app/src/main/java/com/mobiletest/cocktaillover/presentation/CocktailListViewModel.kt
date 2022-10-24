package com.mobiletest.cocktaillover.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiletest.cocktaillover.data.repository.CocktailRepository
import com.mobiletest.cocktaillover.domain.GetPictureSources
import com.mobiletest.cocktaillover.domain.model.PictureSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    private val getPictureSources: GetPictureSources
) : ViewModel() {

    var pictureSourcesLiveData = MutableLiveData<List<PictureSource>>()

    fun onViewCreatedCalled() {

        viewModelScope.launch(Dispatchers.IO) {
            //val cocktails = cocktailRepository.getCocktails()
            val pictureSources = getPictureSources()

            pictureSourcesLiveData.postValue(pictureSources)
        }

    }


}