package br.com.souzabrunoj.service.repository.mappers

import br.com.souzabrunoj.domain.data.response.position.SalaryModel
import br.com.souzabrunoj.service.networking.data.remote.SalaryResponse

fun SalaryResponse?.toSalaryModel(): SalaryModel = SalaryModel(
    range = this?.range ?: "",
    real = this?.real ?: ""
)