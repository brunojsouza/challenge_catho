package br.com.souzabrunoj.repository.mappers

import br.com.souzabrunoj.domain.data.response.tips.TipModel
import br.com.souzabrunoj.domain.data.response.tips.survey.SurveyModel
import br.com.souzabrunoj.service.networking.data.remote.SurveyResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse

fun TipResponse.toTipModel() = TipModel(
    button = this.button.toButtonTioModel(),
    description = this.description ?: "",
    id = this.id ?: ""
)

fun SurveyResponse.toSurveyModel() = SurveyModel(
    action = this.action ?: "",
    createdAt = this.createdAt ?: "",
    id = this.tipId ?: "",
    message = this.getMessage() ?: "",
    tipId = this.tipId ?: ""
)