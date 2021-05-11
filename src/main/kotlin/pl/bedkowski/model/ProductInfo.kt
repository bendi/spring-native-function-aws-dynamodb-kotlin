package pl.bedkowski.model

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
class ProductInfo {
    @get:DynamoDbPartitionKey
    var id: String?
        get() = id
        set(id) {
            this.id = id
        }

    @get:DynamoDbAttribute("msrp")
    var msrp: String?
        get() = msrp
        set(msrp) {
            this.msrp = msrp
        }

    @get:DynamoDbAttribute("cost")
    var cost: String?
        get() = cost
        set(cost) {
            this.cost = cost
        }

    constructor(expectedCost: String?, expectedPrice: String?) {
        this.cost = expectedCost
        this.msrp = expectedPrice
    }

    constructor() {}
}