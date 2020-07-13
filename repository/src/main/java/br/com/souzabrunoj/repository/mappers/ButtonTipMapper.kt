package br.com.souzabrunoj.repository.mappers

import br.com.souzabrunoj.domain.data.response.tips.ButtonTipModel
import br.com.souzabrunoj.service.networking.data.remote.ButtonTipResponse

fun ButtonTipResponse?.toButtonTioModel() = ButtonTipModel(
    label = this?.label ?: "",
    show = this?.show ?: false,
    url = this?.url ?: ""
)