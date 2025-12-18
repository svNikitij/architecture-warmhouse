package ru.svn.transport.input.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.svn.dao.entity.Sensor
import ru.svn.service.SensorService

//TODO после теста вынести в dto
data class SensorValueUpdate(
    val value: Double?,
    val status: String?
)

@RestController
@RequestMapping("/api/v1/sensors")
//TODO позже добавить маппинг в модель запроса/ответа
class SensorController(private val sensorService: SensorService) {

    @GetMapping
    fun getAllSensors(): ResponseEntity<List<Sensor>> {
        return ResponseEntity.ok(sensorService.getAllSensors())
    }

    @GetMapping("/{id}")
    fun getSensorById(@PathVariable id: Long): ResponseEntity<Sensor> {
        return sensorService.getSensorById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createSensor(@RequestBody sensor: Sensor): ResponseEntity<Sensor> {
        val created = sensorService.createSensor(sensor)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @PutMapping("/{id}")
    fun updateSensor(
        @PathVariable id: Long,
        @RequestBody sensor: Sensor
    ): ResponseEntity<Sensor> {
        return sensorService.updateSensor(id, sensor)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PatchMapping("/{id}/value")
    fun updateSensorValue(
        @PathVariable id: Long,
        @RequestBody update: SensorValueUpdate
    ): ResponseEntity<Sensor> {
        return sensorService.updateSensorValue(id, update.value, update.status)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteSensor(@PathVariable id: Long): ResponseEntity<Void> {
        return if (sensorService.deleteSensor(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}