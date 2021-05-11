package pl.bedkowski.model

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
data class ProductInfo(
    @get:DynamoDbPartitionKey
    var id: String? = null,

    @get:DynamoDbAttribute("msrp")
    var msrp: String? = null,

    @get:DynamoDbAttribute("cost")
    var cost: String? = null,
)
