package br.com.souzabrunoj.repository.mappers

import br.com.souzabrunoj.domain.data.response.tips.TipModel
import br.com.souzabrunoj.service.networking.data.remote.TipResponse

fun TipResponse.toTipModel() = TipModel(
    button = this.button.toButtonTioModel(),
    description = this.description ?: "",
    id = this.id ?: ""
)