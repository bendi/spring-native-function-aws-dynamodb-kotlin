package pl.bedkowski.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.nativex.hint.AccessBits
import org.springframework.nativex.hint.NativeHint
import org.springframework.nativex.hint.TypeHint
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

    @Bean
    fun awsCredentials(): AwsCredentials = AwsBasicCredentials.create(dynamoDbConfigProps.accesskey, dynamoDbConfigProps.secretkey)

    @Bean
    fun dynamoDbClient(awsCredentials: AwsCredentials) = DynamoDbClient.builder()
            .credentialsProvider({ awsCredentials })
            .endpointOverride(dynamoDbConfigProps.endpoint)
            .region(dynamoDbConfigProps.region).build()

    @Bean
    fun dynamoDbEnhancedClient(dynamoDbClient: DynamoDbClient) = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build()
}

@Configuration
@Profile("!local")
class DynamoDBConfigRemote(private val dynamoDbConfigProps: DynamoDbConfigProps) {
    @Bean
    fun dynamoDbClient() = DynamoDbClient.builder().region(dynamoDbConfigProps.region).build()

    @Bean
    fun dynamoDbEnhancedClient(dynamoDbClient: DynamoDbClient) = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build()
}

@ConfigurationProperties(prefix = "amazon.dynamodb")
@ConstructorBinding
data class DynamoDbConfigProps(val endpoint: URI, val accesskey: String, val secretkey: String, val region: Region)