package com.devicebooking.example.infra.mongodb.repositories

object DeviceInitialData {
    val INITIAL_DEVICE_DATA_LIST = listOf(
        DeviceInitialInfo(
            model = "Samsung Galaxy S9",
            technology = "GSM / CDMA / HSPA / EVDO / LTE",
            bands2G = "GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM model only)",
            bands3G = "HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100",
            bands4G = "LTE band 1(2100), 2(1900), 3(1800), 4(1700/2100), 5(850), 7(2600), 8(900), 12(700), 13(700), 17(700), 18(800), 19(800), 20(800), 25(1900), 26(850), 28(700), 32(1500), 38(2600), 39(1900), 40(2300), 41(2500), 66(1700/2100)",
            amount = 1
        ),
        DeviceInitialInfo(
            model = "Samsung Galaxy S8",
            technology = "GSM / HSPA / LTE 2G",
            bands2G = "GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM model only)",
            bands3G = "HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100",
            bands4G = "LTE band 1(2100), 2(1900), 3(1800), 4(1700/2100), 5(850), 7(2600), 8(900), 12(700), 13(700), 17(700), 18(800), 19(800), 20(800), 25(1900), 26(850), 28(700), 32(1500), 66(1700/2100), 38(2600), 39(1900), 40(2300), 41(2500)",
            amount = 2
        ),
        DeviceInitialInfo(
            model = "Motorola Nexus 6",
            technology = "GSM / CDMA / HSPA / LTE",
            bands2G = "GSM 850 / 900 / 1800 / 1900",
            bands3G = "HSDPA 800 / 850 / 900 / 1700(AWS) / 1800 / 1900 / 2100",
            bands4G = "LTE band 1(2100), 2(1900), 3(1800), 4(1700/2100), 5(850), 7(2600), 8(900), 9(1800), 19(800), 20(800), 28(700), 41(2500)",
            amount = 1
        ),
        DeviceInitialInfo(
            model = "OnePlus 9",
            technology = "GSM / CDMA / HSPA / LTE / 5G",
            bands2G = "GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2",
            bands3G = "HSDPA 800 / 850 / 900 / 1700(AWS) / 1800 / 1900 / 2100",
            bands4G = "LTE band 1(2100), 2(1900), 3(1800), 4(1700/2100), 5(850), 7(2600), 8(900), 12(700), 13(700), 17(700), 18(800), 19(800), 20(800), 25(1900), 26(850), 28(700), 32(1500), 34(2000), 38(2600), 39(1900), 40(2300), 41(2500), 42(3500), 66(1700/2100)",
            amount = 1
        ),
        DeviceInitialInfo(
            model = "Apple iPhone 13",
            technology = "GSM / CDMA / HSPA / EVDO / LTE / 5G",
            bands2G = "GSM 850 / 900 / 1800 / 1900",
            bands3G = "HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100",
            bands4G = "LTE 5G Bands: Sub6/mmWave",
            amount = 1
        ),
        DeviceInitialInfo(
            model = "Apple iPhone 12",
            technology = "GSM / CDMA / HSPA / EVDO / LTE / 5G",
            bands2G = "GSM 850 / 900 / 1800 / 1900",
            bands3G = "HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100",
            bands4G = "LTE 5G Bands: Sub6/mmWave",
            amount = 1
        ),
        DeviceInitialInfo(
            model = "Apple iPhone 11",
            technology = "GSM / CDMA / HSPA / EVDO / LTE",
            bands2G = "GSM 850 / 900 / 1800 / 1900",
            bands3G = "HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100",
            bands4G = "LTE",
            amount = 1
        ),
        DeviceInitialInfo(
            model = "iPhone X",
            technology = "GSM / CDMA / HSPA / EVDO / LTE",
            bands2G = "GSM 850 / 900 / 1800 / 1900",
            bands3G = "HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100",
            bands4G = "LTE",
            amount = 1
        ),
        DeviceInitialInfo(
            model = "Nokia 3310",
            technology = "GSM 2G",
            bands2G = "GSM 900 / 1800",
            bands3G = "N/A",
            bands4G = "N/A",
            amount = 1
        )
    )
}

data class DeviceInitialInfo(
    val model: String,
    val technology: String,
    val bands2G: String,
    val bands3G: String,
    val bands4G: String,
    val amount: Int
)
