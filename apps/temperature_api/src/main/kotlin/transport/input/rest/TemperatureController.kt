package ru.svn.transport.input.rest

import org.springframework.web.bind.annotation.*
import kotlin.math.roundToInt
import kotlin.random.Random

//TODO после теста вынести в dto
data class TemperatureResponse(
    val sensorId: String,
    val status: String,
    val value: Double,
    val unit: String = "°C"
)

@RestController
class TemperatureController {

    @GetMapping("/temperature/{sensorIdParam}")
    fun getTemperature(
        @PathVariable sensorIdParam: String,
        @RequestParam(required = false) location: String?,
    ): TemperatureResponse {

        var loc = location ?: ""
        var sensorId = sensorIdParam

        //TODO вынести эту логику в сервис

        // If no location is provided, use a default based on sensor ID
        if (loc.isEmpty()) {
            loc = when (sensorId) {
                "1" -> "Living Room"
                "2" -> "Bedroom"
                "3" -> "Kitchen"
                else -> "Unknown"
            }
        }

        // If no sensor ID is provided, generate one based on location
        if (sensorId.isEmpty()) {
            sensorId = when (loc) {
                "Living Room" -> "1"
                "Bedroom" -> "2"
                "Kitchen" -> "3"
                else -> "0"
            }
        }

        val temperature = (Random.nextDouble(15.0, 30.0) * 10.0).roundToInt() / 10.0

        return TemperatureResponse(
            sensorId = sensorId,
            status = "active",
            value = temperature
        )
    }
}