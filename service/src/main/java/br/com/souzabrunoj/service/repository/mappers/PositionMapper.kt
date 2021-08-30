package br.com.souzabrunoj.service.repository.mappers

import br.com.souzabrunoj.domain.data.response.position.PositionModel
import br.com.souzabrunoj.service.networking.data.remote.PositionResponse

fun PositionResponse.toPositionModel() = PositionModel(
    company = this.company ?: "",
    date = this.date ?: "",
    jobAdTile = this.jobAdTile ?: "",
    locations = this.locations ?: listOf(),
    salaryModel = this.salaryModel.toSalaryModel(),
    totalPositions = this.totalPositions ?: 0
)