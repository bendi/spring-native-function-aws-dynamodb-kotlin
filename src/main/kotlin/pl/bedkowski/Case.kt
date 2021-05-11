package pl.bedkowski

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import pl.bedkowski.repository.ProductInfoRepository

@Component
class Case {
    companion object {
        private val log:Logger = LogManager.getLogger()
    }

    @Bean
    fun uppercase(productInfoRepository: ProductInfoRepository): (String) -> String {
        return  {
            val product = productInfoRepository.findById("1")
            log.info("Fetched product: {}", product.msrp)

            it.uppercase()
        }
    }

    @Bean
    fun lowercase(): (String) -> String {
        return  { it.lowercase() }
    }
}