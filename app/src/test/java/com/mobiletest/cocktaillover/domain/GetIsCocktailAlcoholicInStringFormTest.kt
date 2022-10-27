package com.mobiletest.cocktaillover.domain

import com.google.common.truth.Truth
import org.junit.Test

class GetIsCocktailAlcoholicInStringFormTest {

    private val getIsCocktailAlcoholicInStringForm = GetIsCocktailAlcoholicInStringForm()

    @Test
    fun test_with_FalseValue() {
        getIsCocktailAlcoholicInStringForm(false).let {
            Truth.assertThat(it).isEqualTo("Non-Alcoholic")
        }
    }

    @Test
    fun test_with_TrueValue() {
        getIsCocktailAlcoholicInStringForm(true).let {
            Truth.assertThat(it).isEqualTo("Alcoholic")
        }
    }

}