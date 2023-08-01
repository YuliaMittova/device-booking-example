package com.devicebooking.example.infra.mongodb.repositories

import com.devicebooking.example.core.gateways.DeviceInfoDataGateway
import com.devicebooking.example.core.models.DeviceInfo
import com.devicebooking.example.infra.mongodb.config.MongoRepository
import com.devicebooking.example.infra.mongodb.entities.DeviceInfoEntity
import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.IndexModel
import com.mongodb.client.model.Indexes
import com.mongodb.client.model.UpdateOptions
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.bson.Document

@Singleton
class DeviceInfoRepository :
    MongoRepository<DeviceInfoEntity>(DeviceInfoEntity::class.java),
    DeviceInfoDataGateway {

    override fun getIndexes(): List<IndexModel> {
        val nameIndex = IndexModel(
            Indexes.ascending(DeviceInfoEntity::model.name)
        )
        return listOf(nameIndex)
    }

    override suspend fun loadInitialData() {
        // no need to initialize data if it is already there
        if (getCollection().countDocuments().awaitFirst() > 0) return

        val resultList = mutableListOf<DeviceInfoEntity>()
        for (initialData in DeviceInitialData.INITIAL_DEVICE_DATA_LIST) {
            val amount = initialData.amount
            for (i in 1..amount) {
                resultList.add(
                    DeviceInfoEntity(
                        id = null,
                        model = initialData.model,
                        technology = initialData.technology,
                        bands2G = initialData.bands2G,
                        bands3G = initialData.bands3G,
                        bands4G = initialData.bands4G,
                        lastBookedBy = null,
                        lastBookedAt = null,
                        isAvailable = true
                    )
                )
            }
        }
        insertMany(resultList)
    }

    override suspend fun getByModel(model: String): Result<List<DeviceInfo>> {
        val result = getCollection()
            .find(and(eq(DeviceInfoEntity::model.name, model)))
            .asFlow()
            .map { it.toModel() }
            .toList()

        return Result.success(result)
    }

    override suspend fun updateDeviceAvailability(
        deviceInfo: DeviceInfo,
        newAvailability: Boolean,
        updatedBy: String
    ): Result<DeviceInfo?> {
        val entity = DeviceInfoEntity.fromModel(deviceInfo).copy(
            isAvailable = newAvailability,
            lastBookedBy = updatedBy,
            lastBookedAt = System.currentTimeMillis()
        )
        val filter = entity.id?.let {
            eq("_id", it)
        } ?: and(
            eq("model", entity.model),
        )

        val result =
            getCollection().updateOne(filter, Document("\$set", entity), UpdateOptions().upsert(false)).awaitFirst()
        return if (result.wasAcknowledged()) {
            Result.success(entity.toModel())
        } else {
            Result.failure(RuntimeException("Failed to update device info for model ${deviceInfo.model}"))
        }
    }
}
