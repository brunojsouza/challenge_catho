package br.com.souzabrunoj.repository

import br.com.souzabrunoj.domain.common.*
import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import br.com.souzabrunoj.domain.data.response.tips.TipModel
import br.com.souzabrunoj.repository.mappers.toLoginModel
import br.com.souzabrunoj.repository.mappers.toPositionModel
import br.com.souzabrunoj.repository.mappers.toTipModel
import br.com.souzabrunoj.service.networking.Networking
import br.com.souzabrunoj.service.networking.data.local.PreferencesService

class RepositoryImpl(private val networking: Networking, private val preferencesService: PreferencesService) : Repository, BaseRepository() {

    override suspend fun getApiKey(): Either<Failure.ServiceError, Unit> =
        shiftThread {
            preferencesService.saveKeys(networking.getApiKey())
            Success(Unit)
        }

    override suspend fun doLogin(userId: String): Either<Failure.ServiceError, LoginModel> {
        return shiftThread {
            val response = networking.doLogin(preferencesService.getApiKey(AUTH_KEY), userId).toLoginModel()
            preferencesService.saveUser(response)
            Success(response)
        }
    }

    override suspend fun getPositions(): Either<Failure.ServiceError, List<PositionModel>> =
        shiftThread { Success(networking.getPositions(preferencesService.getApiKey(SUGGESTION_KEY)).map { it.toPositionModel() }) }

    override suspend fun getTips(): Either<Failure.ServiceError, List<TipModel>> =
        shiftThread { Success(networking.getTips(preferencesService.getApiKey(TIPS_KEY)).map { it.toTipModel() }) }
}