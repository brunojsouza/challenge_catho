package br.com.souzabrunoj.challenge_catho.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.souzabrunoj.domain.position.PositionModel
import br.com.souzabrunoj.domain.position.SalaryModel

class HomeViewModel: ViewModel() {

    private val positions = MutableLiveData<List<PositionModel>>()
    fun positionsObserver(): LiveData<List<PositionModel>> = positions

    fun getPositions() {
        positions.value = listOf(
            PositionModel(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 2,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP), Belo Horizonte(MG"),
                salaryModel = SalaryModel(range = "A Combinar", real = "")
            ),
            PositionModel(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 1,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP)"),
                salaryModel = SalaryModel(range = "A Combinar", real = "R$ 8.500,00")
            ),
            PositionModel(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 3,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP), Belo Horizonte(MG)", "Buzios (RJ)"),
                salaryModel = SalaryModel(range = "A Combinar", real = "")
            ),
            PositionModel(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 1,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP)"),
                salaryModel = SalaryModel(range = "A Combinar", real = "")
            ),
            PositionModel(
                company = "CATHO",
                date = "Hoje",
                totalPositions = 1,
                jobAdTile = "Desenvolvedor Mobile - Android",
                locations = listOf("Barueri (SP)"),
                salaryModel = SalaryModel(range = "A Combinar", real = "")
            )
        )
    }

}