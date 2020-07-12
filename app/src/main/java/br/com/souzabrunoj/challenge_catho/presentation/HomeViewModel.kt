package br.com.souzabrunoj.challenge_catho.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.souzabrunoj.domain.login.LoginModel
import br.com.souzabrunoj.domain.position.PositionModel
import br.com.souzabrunoj.domain.position.SalaryModel
import br.com.souzabrunoj.domain.tips.ButtonTipModel
import br.com.souzabrunoj.domain.tips.TipModel

class HomeViewModel: ViewModel() {

    private val positions = MutableLiveData<List<PositionModel>>()
    private val login = MutableLiveData<LoginModel>()
    private val tips = MutableLiveData<List<TipModel>>()

    fun positionsObserver(): LiveData<List<PositionModel>> = positions
    fun loginObserver(): LiveData<LoginModel> = login
    fun tipObserver(): LiveData<List<TipModel>> = tips

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

    fun doLogin() {
        login.value = LoginModel(
            id = "ee09bd39-4ca2-47ac-9c5e-9c57ba5a26dc",
            name = "Leticia da Silva",
            token = "jpfakdbfprfpirfpwfpwbrpfibwdpcvbspkjvbpkfv",
            photoUrl = "https://picsum.photos/200/300?random=16"
        )
    }

    fun getTips() {
        tips.value = listOf(
            TipModel(
                button = ButtonTipModel(
                    label = "checar curriculo", show = true, url = "https://www.catho.com.br"
                ),
                description = "Antes de enviar o seu currículo, que tal checar a última vez que você o atualizou? Uma informação a mais pode ser o ponta-pé que falta rumo à sua próxima entrevista de emprego.",
                id = "ea9ff33d-16db-42e8-9913-3fb52f3cb992"
            ),
            TipModel(
                button = ButtonTipModel(
                    label = "Dica 1", show = true, url = "https://www.catho.com.br"
                ),
                description = "Antes de enviar o seu currículo, que tal checar a última vez que você o atualizou? Uma informação a mais pode ser o ponta-pé que falta rumo à sua próxima entrevista de emprego.",
                id = "ea9ff33d-16db-42e8-9913-3fb52f3cb992"
            ),
            TipModel(
                button = ButtonTipModel(
                    label = "Dica 2", show = true, url = "https://www.catho.com.br"
                ),
                description = "Antes de enviar o seu currículo, que tal checar a última vez que você o atualizou? Uma informação a mais pode ser o ponta-pé que falta rumo à sua próxima entrevista de emprego.",
                id = "ea9ff33d-16db-42e8-9913-3fb52f3cb992"
            )
        )
    }

}