package br.com.souzabrunoj.repository

import br.com.souzabrunoj.domain.common.Either
import br.com.souzabrunoj.domain.common.Failure
import br.com.souzabrunoj.domain.common.Success
import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import br.com.souzabrunoj.domain.data.response.tips.TipModel
import br.com.souzabrunoj.repository.mappers.toLoginModel
import br.com.souzabrunoj.repository.mappers.toPositionModel
import br.com.souzabrunoj.repository.mappers.toTipModel
import br.com.souzabrunoj.service.networking.Networking

class RepositoryImpl(private val networking: Networking) : Repository, BaseRepository() {

    override suspend fun doLogin(): Either<Failure.ServiceError, LoginModel> =
        shiftThread { Success(networking.doLogin().toLoginModel()) }

    override suspend fun getPositions(): Either<Failure.ServiceError, List<PositionModel>> =
        shiftThread { Success(networking.getPositions().map { it.toPositionModel() }) }

    override suspend fun getTips(): Either<Failure.ServiceError, List<TipModel>> =
        shiftThread { Success(networking.getTips().map { it.toTipModel() }) }
}