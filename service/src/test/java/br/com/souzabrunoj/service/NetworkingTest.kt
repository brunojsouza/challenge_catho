package br.com.souzabrunoj.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import br.com.souzabrunoj.service.networking.Networking
import br.com.souzabrunoj.service.networking.NetworkingImpl
import br.com.souzabrunoj.service.networking.WebService
import br.com.souzabrunoj.service.networking.data.mock.doLoginFromMock
import br.com.souzabrunoj.service.networking.data.mock.getApiKeysFromMock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest


@ExperimentalCoroutinesApi
class NetworkingTest : KoinTest {

    private val webService = mockk<WebService>()
    private lateinit var networking: Networking

    private val testModule = module {
        single { webService }
    }

    @Before
    fun setup() {
        startKoin { modules(testModule) }
        networking = NetworkingImpl(webService)
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun `when get api key return success`() = runBlocking {

        coEvery { networking.getApiKey() } returns getApiKeysFromMock()

        val response = webService.getApiKey()

        coVerify(exactly = 1) { networking.getApiKey() }

        assertThat(response).isEqualTo(getApiKeysFromMock())
    }

    @Test
    fun `when login return success`() = runBlocking {

        val apiKey = "1811a58a2bdceefa000c5b4bb1def4e00c8151de2e3fc1180849ce12134d763ad0416a71fd2cd59241a30a225596ecf4020498ad4b9be3b5f9220e852b86defb"
        val userId ="ee09bd39-4ca2-47ac-9c5e-9c57ba5a26dc"

        coEvery { networking.doLogin(apiKey, userId) } returns doLoginFromMock()

        val response = webService.doLogin(apiKey, userId)

        coVerify(exactly = 1) {
            networking.doLogin(apiKey, userId)
        }

        assertThat(response).isEqualTo(doLoginFromMock())

    }
}
