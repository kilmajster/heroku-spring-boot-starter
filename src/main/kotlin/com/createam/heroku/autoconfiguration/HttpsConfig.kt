package com.createam.heroku.autoconfiguration

import com.createam.heroku.https.HttpsEnforcer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.createam.heroku"])
class HttpsConfig {

    var logger: Logger = LoggerFactory.getLogger(HttpsConfig::class.java)

    @Bean
    @ConditionalOnProperty(name = ["heroku.enforceHttps"], havingValue = "true")
    fun httpsEnforcer(): HttpsEnforcer {
        logger.info("Heroku https enforce is enable")
        return HttpsEnforcer()
    }
}

