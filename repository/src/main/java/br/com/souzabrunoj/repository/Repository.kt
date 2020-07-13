package br.com.souzabrunoj.repository

import br.com.souzabrunoj.domain.common.Either
import br.com.souzabrunoj.domain.common.Failure
import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import br.com.souzabrunoj.domain.data.response.tips.TipModel
import br.com.souzabrunoj.domain.data.response.tips.survey.SurveyModel

interface Repository {

    suspend fun getApiKey():  Either<Failure.ServiceError, Unit>

    suspend fun doLogin(userId: String): Either<Failure.ServiceError, LoginModel>

    suspend fun getPositions(): Either<Failure.ServiceError, List<PositionModel>>

    suspend fun getTips(): Either<Failure.ServiceError, List<TipModel>>

    suspend fun sendTipSurvey(tipId: String, interactionType: String): Either<Failure.ServiceError, SurveyModel>
}