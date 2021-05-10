package pl.bedkowski

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class Case {

    @Bean
    fun uppercase(): (String) -> String {
        return  { it.toUpperCase() }
    }

    @Bean
    fun lowercase(): (String) -> String {
        return  { it.toLowerCase() }
    }
}