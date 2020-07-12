package br.com.souzabrunoj.challenge_catho.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.souzabrunoj.domain.position.Position
import br.com.souzabrunoj.domain.position.Salary

class HomeViewModel: ViewModel() {

    private val positions = MutableLiveData<List<Position>>()
    fun positionsObserver(): LiveData<List<Position>> = positions

    fun getPositions() {
        positions.value = listOf(
            Position(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 2,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP), Belo Horizonte(MG"),
                salary = Salary(range = "A Combinar", real = "")
            ),
            Position(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 1,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP)"),
                salary = Salary(range = "A Combinar", real = "R$ 8.500,00")
            ),
            Position(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 3,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP), Belo Horizonte(MG)", "Buzios (RJ)"),
                salary = Salary(range = "A Combinar", real = "")
            ),
            Position(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 1,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP)"),
                salary = Salary(range = "A Combinar", real = "")
            ),
            Position(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 1,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP)"),
                salary = Salary(range = "A Combinar", real = "")
            )
        )
    }

}