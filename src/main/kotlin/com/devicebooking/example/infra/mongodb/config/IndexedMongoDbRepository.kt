package com.devicebooking.example.infra.mongodb.config

import com.mongodb.client.model.IndexModel
import com.mongodb.reactivestreams.client.MongoCollection
import org.bson.Document

interface IndexedMongoDbRepository<T> {
    fun getCollection(): MongoCollection<T>

    fun getCollectionAsDocument(): MongoCollection<Document>

    fun getIndexes(): List<IndexModel>

    suspend fun loadInitialData()
}
