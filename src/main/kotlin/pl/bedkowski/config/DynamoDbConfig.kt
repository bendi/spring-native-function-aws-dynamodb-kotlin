package pl.bedkowski.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Configuration
@Profile("local")
class DynamoDBConfigLocal(private val dynamoDbConfigProps: DynamoDbConfigProps) {

    fun awsCredentials() = AwsBasicCredentials.create(dynamoDbConfigProps.accesskey, dynamoDbConfigProps.secretkey)

    fun dynamoDbClient(awsCredentials: AwsCredentials) = DynamoDbClient.builder()
            .credentialsProvider({ awsCredentials })
            .endpointOverride(dynamoDbConfigProps.endpoint)
            .region(dynamoDbConfigProps.region).build()

    @Bean
    fun dynamoDbEnhancedClient() = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient(awsCredentials())).build()
}

@Configuration
@Profile("!local")
class DynamoDBConfigRemote(private val dynamoDbConfigProps: DynamoDbConfigProps) {
    fun dynamoDbClient() = DynamoDbClient.builder().region(dynamoDbConfigProps.region).build()

    @Bean
    fun dynamoDbEnhancedClient() = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient()).build()
}

@ConfigurationProperties(prefix = "amazon.dynamodb")
@ConstructorBinding
data class DynamoDbConfigProps(val endpoint: URI, val accesskey: String, val secretkey: String, val region: Region)