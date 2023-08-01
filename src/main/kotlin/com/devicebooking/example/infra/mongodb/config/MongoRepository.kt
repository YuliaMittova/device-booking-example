package com.devicebooking.example.infra.mongodb.config

import com.mongodb.client.result.InsertManyResult
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import jakarta.inject.Inject
import kotlinx.coroutines.reactive.awaitFirst
import org.bson.Document

abstract class MongoRepository<T>(private val clazz: Class<T>) : IndexedMongoDbRepository<T> {
    @Inject
    private lateinit var mongoConfig: MongoDbConfig

    @Inject
    private lateinit var client: MongoClient

    override fun getCollection(): MongoCollection<T> =
        client.getDatabase(mongoConfig.databaseName).getCollection(clazz.simpleName, clazz)

    override fun getCollectionAsDocument(): MongoCollection<Document> =
        client.getDatabase(mongoConfig.databaseName).getCollection(clazz.simpleName)

    suspend fun insertMany(entities: List<T>): InsertManyResult = getCollection().insertMany(entities).awaitFirst()
}
