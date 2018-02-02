package com.createam.heroku.autoconfiguration

import com.createam.heroku.thymeleaf.HttpsEnforcer
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.createam.heroku.thymeleaf"])
class ThymeleafConfig {

    private val logger = LoggerFactory.getLogger(ThymeleafConfig::class.java)

    @Bean
    @ConditionalOnProperty(name = ["heroku.thymeleaf.enforceHttps"], havingValue = "true")
    fun httpsEnforcer(): HttpsEnforcer {
        logger.info("Heroku https enforce is enable")
        return HttpsEnforcer()
    }
}

