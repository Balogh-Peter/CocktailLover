package com.mobiletest.cocktaillover.viewmodel

import com.google.common.truth.Truth
import com.mobiletest.cocktaillover.domain.model.Cocktail
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import com.mobiletest.cocktaillover.presentation.instructions_screen.InstructionsViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class InstructionsViewModelTest {


    private lateinit var viewModel: InstructionsViewModel

    @Before
    fun setup() {
        viewModel = InstructionsViewModel()
    }

    @Test
    fun test_InitialValues() {
        viewModel.instructions.value.let {
            Truth.assertThat(it).isEqualTo("")
        }
        viewModel.cocktailData.let {
            Truth.assertThat(it).isNull()
        }
    }

    @Test
    fun test_AfterCallingOnCreate() {
        val cocktail = Cocktail(
            drinkId = "",
            name = "",
            category = "",
            isAlcoholic = true,
            recommendedGlassType = "",
            instructions = "Meg kell inni",
            thumbnailUrl = ""
        )
        val cocktailData = CocktailWithPictureSource(
            byteArray = ByteArray(0),
            cocktail = cocktail
        )
        viewModel.onCreateCalled(cocktailData)


        viewModel.instructions.value.let {
            Truth.assertThat(it).isEqualTo("Meg kell inni")
        }
        viewModel.cocktailData!!.cocktail.instructions.let {
            Truth.assertThat(it).isEqualTo("Meg kell inni")
        }
    }

}