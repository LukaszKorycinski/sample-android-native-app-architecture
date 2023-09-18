package com.sampleandroidarchitecture

import com.sampleandroidarchitecture.data.repository.LocalRepository
import com.sampleandroidarchitecture.data.repository.WeatherRepository
import com.sampleandroidarchitecture.ui.screens.city_search.CitySearchViewModel
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CitySearchTest {

    infix fun Int.razyI_podzielic_na_dwa(x: Int): Int{
        return (this * x) / 2
    }

    @Test
    fun main(){

        val a = 3
        val b = 4

        assertEquals( a razyI_podzielic_na_dwa b, 6)

    }




    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun validateCityNameTest() {
        val weatherRepository = mockk<WeatherRepository>(relaxed = true)
        val localRepository = mockk<LocalRepository>(relaxed = true)

        val vm = CitySearchViewModel(weatherRepository, localRepository)

        val validCityNames = listOf(
            "Łódz",
            "Szczecin",
            "Bydgoszcz",
            "Lublin",
            "Białystok",
            "New York",
            "Hanoi",
            "St. Catharines",
            "San Fransisco",
            "Val-d'Or",
            "Presqu'ile",
            "Niagara on the Lake",
            "Niagara-on-the-Lake",
            "München",
            "toronto",
            "toRonTo",
            "villes du Québec",
            "Provence-Alpes-Côte d'Azur",
            "Île-de-France",
            "Kópavogur",
            "Garðabær",
            "Sauðárkrókur",
            "Þorlákshöfn",
            "Y"//city in France :)
        )

        val invalidCityNames = listOf(
            "Warsaw1",
            "2Krakow",
            "Wro@claw",
            "Gd%ansk",
            "A----B",
            "------",
            "*******",
            "&&",
            "()",
        )

        validCityNames.forEach {
            assertTrue("Fail for city name: $it", vm.validateCityName(it))
        }

        invalidCityNames.forEach {
            assertFalse("Fail for city name: $it", vm.validateCityName(it))
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}