package com.devicebooking.example.infra.mongodb.entities

import com.devicebooking.example.core.models.DeviceInfo
import io.micronaut.core.annotation.Creator
import io.micronaut.core.annotation.Introspected
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

@Introspected
data class DeviceInfoEntity
@Creator
@BsonCreator
constructor(
    @param:BsonId val id: ObjectId?,
    @param:BsonProperty("model") val model: String,
    @param:BsonProperty("lastBookedBy") val lastBookedBy: String? = null,
    @param:BsonProperty("lastBookedAt") val lastBookedAt: Long? = null, // in epoch millis
    @param:BsonProperty("available") val isAvailable: Boolean, // is this device currently available
    @param:BsonProperty("technology") val technology: String,
    @param:BsonProperty("bands2G") val bands2G: String,
    @param:BsonProperty("bands3G") val bands3G: String,
    @param:BsonProperty("bands4G") val bands4G: String,
) {

    fun toModel(): DeviceInfo {
        return DeviceInfo(
            id = id?.toString(),
            model = model,
            technology = technology,
            bands2G = bands2G,
            bands3G = bands3G,
            bands4G = bands4G,
            lastBookedBy = lastBookedBy,
            lastBookedAt = lastBookedAt,
            isAvailable = isAvailable
        )
    }

    companion object {
        fun fromModel(
            deviceInfo: DeviceInfo
        ) = DeviceInfoEntity(
            id = deviceInfo.id?.let { ObjectId(it) },
            model = deviceInfo.model,
            technology = deviceInfo.technology,
            bands2G = deviceInfo.bands2G,
            bands3G = deviceInfo.bands3G,
            bands4G = deviceInfo.bands4G,
            lastBookedBy = deviceInfo.lastBookedBy,
            lastBookedAt = deviceInfo.lastBookedAt,
            isAvailable = deviceInfo.isAvailable
        )
    }
}
