package pl.bedkowski

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import pl.bedkowski.config.DynamoDbConfigProps

@SpringBootApplication
@ConfigurationPropertiesScan("pl.bedkowski.config")
// limitation of native-image generation
// please see https://github.com/spring-projects-experimental/spring-native/issues/716
@EnableConfigurationProperties(DynamoDbConfigProps::class)
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
