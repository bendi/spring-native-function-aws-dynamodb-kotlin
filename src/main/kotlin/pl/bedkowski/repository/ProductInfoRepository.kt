package pl.bedkowski.repository

import org.springframework.stereotype.Component
import pl.bedkowski.model.ProductInfo
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema

@Component
class ProductInfoRepository(dynamoDbEnhancedClient: DynamoDbEnhancedClient) {
    companion object {
        private val SCHEMA: TableSchema<ProductInfo>
        private fun tableSchema(): TableSchema<ProductInfo> {
            return StaticTableSchema.builder(ProductInfo::class.java)
                    .newItemSupplier({ ProductInfo() })
                    .addAttribute(
                            String::class.java,
                            {
                                it.name("id")
                                        .getter(ProductInfo::id::get)
                                        .setter(ProductInfo::id::set)
                                        .tags(StaticAttributeTags.primaryPartitionKey())
                            }
                    )
                    .addAttribute(
                            String::class.java,
                            {
                                it.name("msrp")
                                        .getter(ProductInfo::msrp::get)
                                        .setter(ProductInfo::msrp::set)
                            }
                    )
                    .addAttribute(
                            String::class.java,
                            {
                                it.name("cost")
                                        .getter(ProductInfo::cost::get)
                                        .setter(ProductInfo::cost::set)
                            }
                    )
                    .build()
        }

        init {
//         BeanTableSchema is buggy on graalvm: https://github.com/aws/aws-sdk-java-v2/issues/2445
//         building static
//        SCHEMA = TableSchema.fromBean(ProductInfo.class);
            ProductInfoRepository.Companion.SCHEMA = ProductInfoRepository.Companion.tableSchema()
        }
    }

    private val dynamoDbTable: DynamoDbTable<ProductInfo>
    fun findById(keyVal: String?): ProductInfo {
        val key: Key = Key.builder().partitionValue(keyVal).build()
        return dynamoDbTable.getItem(key)
    }

    init {
        dynamoDbTable = dynamoDbEnhancedClient.table("ProductInfo", ProductInfoRepository.Companion.SCHEMA)
    }
}