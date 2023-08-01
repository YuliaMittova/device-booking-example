package com.devicebooking.example.infra.mongodb.config

import io.micronaut.context.ApplicationContext
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

internal fun ApplicationContext.initMongoDbIndexes(): ApplicationContext {
    val mongodbInit = MongoDbInitializer(this)

    runBlocking {
        mongodbInit.initializeIndexes()
        mongodbInit.initializeInitialData()
    }

    return this
}

class MongoDbInitializer(private val context: ApplicationContext) {
    private val logger = LoggerFactory.getLogger(MongoDbInitializer::class.java)

    suspend fun initializeIndexes() {
        logger.debug("Initializing indexes for MongoDb repository...")

        context.getBeansOfType(IndexedMongoDbRepository::class.java).forEach { repository ->
            logger.debug("Creating indexes for ${repository.javaClass.simpleName}")
            repository.getCollection().createIndexes(repository.getIndexes()).awaitFirst()
        }
    }

    suspend fun initializeInitialData() {
        logger.debug("Initializing initial data for MongoDb repository...")

        context.getBeansOfType(IndexedMongoDbRepository::class.java).forEach { repository ->
            logger.debug("Adding initial data for ${repository.javaClass.simpleName}")
            repository.loadInitialData()
        }
    }
}
