package com.createam.heroku.autoconfiguration

import com.createam.heroku.https.HttpsEnforcer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.slf4j.Logger


@RunWith(MockitoJUnitRunner::class)
class HttpsConfigTest {

    private val mockedLogger = Mockito.mock(Logger::class.java)

    @Test
    fun shouldCreateHttpsEnforcer() {
        val httpsConfig = HttpsConfig()

        val enforcer = httpsConfig.httpsEnforcer()

        assertThat(enforcer).isNotNull()
        assertThat(enforcer).isInstanceOf(HttpsEnforcer::class.java)
    }

    @Test
    fun shouldLogThatEnforcerIsEnable() {
        val httpsConfig = HttpsConfig()
        httpsConfig.logger = mockedLogger

        httpsConfig.httpsEnforcer()

        verify(mockedLogger).info("Heroku https enforce is enable")
    }
}
