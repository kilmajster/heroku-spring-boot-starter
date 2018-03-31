package com.createam.heroku.autoconfiguration

import com.createam.heroku.thymeleaf.HttpsEnforcer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import org.slf4j.Logger


@RunWith(MockitoJUnitRunner::class)
class ThymeleafConfigTest {

    private val mockedLogger = Mockito.mock(Logger::class.java)

    @Test
    fun shouldCreateHttpsEnforcer() {
        val thymeleafConfig = ThymeleafConfig()

        val enforcer = thymeleafConfig.httpsEnforcer()

        assertThat(enforcer).isNotNull()
        assertThat(enforcer).isInstanceOf(HttpsEnforcer::class.java)
    }

    @Test
    fun shouldLogThatEnforcerIsEnable() {
        val thymeleafConfig = ThymeleafConfig()
        thymeleafConfig.logger = mockedLogger

        thymeleafConfig.httpsEnforcer()

        Mockito.verify(mockedLogger, Mockito.times(1)).info("Heroku https enforce is enable")
    }
}
