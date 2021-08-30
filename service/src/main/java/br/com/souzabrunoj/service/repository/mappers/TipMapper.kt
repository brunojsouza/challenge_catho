package br.com.souzabrunoj.service.repository.mappers

import br.com.souzabrunoj.domain.data.response.tips.TipModel
import br.com.souzabrunoj.domain.data.response.tips.survey.SurveyModel
import br.com.souzabrunoj.service.networking.data.remote.SurveyResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse

fun TipResponse.toDomain() = TipModel(
    button = this.button.toDomain(),
    description = this.description ?: "",
    id = this.id ?: ""
)

fun SurveyResponse.toDomain() = SurveyModel(
    action = this.action ?: "",
    createdAt = this.createdAt ?: "",
    id = this.tipId ?: "",
    message = this.getMessage() ?: "",
    tipId = this.tipId ?: ""
)