package com.mobiletest.cocktaillover.viewmodel

import com.google.common.truth.Truth
import com.mobiletest.cocktaillover.domain.GetIsCocktailAlcoholicInStringForm
import com.mobiletest.cocktaillover.domain.model.Cocktail
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import com.mobiletest.cocktaillover.presentation.cocktail_details_screen.CocktailDetailsViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class CocktailDetailsViewModelTest {

    private lateinit var viewModel: CocktailDetailsViewModel
    private lateinit var getIsCocktailAlcoholicInStringForm: GetIsCocktailAlcoholicInStringForm

    @Before
    fun setup() {
        getIsCocktailAlcoholicInStringForm = GetIsCocktailAlcoholicInStringForm()
        viewModel = CocktailDetailsViewModel(getIsCocktailAlcoholicInStringForm)
    }

    @Test
    fun test_InitialValues() {
        viewModel.isAlcoholic.value.let {
            Truth.assertThat(it).isEqualTo("")
        }
        viewModel.name.value.let {
            Truth.assertThat(it).isNull()
        }
        viewModel.category.value.let {
            Truth.assertThat(it).isNull()
        }
        viewModel.recommendedGlassType.value.let {
            Truth.assertThat(it).isNull()
        }
    }

    @Test
    fun test_AfterCallingOnCreate() {
        val cocktail = Cocktail(
            drinkId = "",
            name = "Bitang Pusztulat",
            category = "test category",
            isAlcoholic = true,
            recommendedGlassType = "Ikeás",
            instructions = "",
            thumbnailUrl = ""
        )
        val cocktailData = CocktailWithPictureSource(
            byteArray = ByteArray(0),
            cocktail = cocktail
        )
        viewModel.onCreateCalled(cocktailData)


        viewModel.isAlcoholic.value.let {
            Truth.assertThat(it).isEqualTo("Alcoholic")
        }
        viewModel.name.value.let {
            Truth.assertThat(it).isEqualTo("Bitang Pusztulat")
        }
        viewModel.category.value.let {
            Truth.assertThat(it).isEqualTo("test category")
        }
        viewModel.recommendedGlassType.value.let {
            Truth.assertThat(it).isEqualTo("Ikeás")
        }
    }

}