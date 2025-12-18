package ru.svn.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.svn.dao.entity.Sensor
import ru.svn.dao.repository.SensorRepository
import java.time.LocalDateTime

@Service
@Transactional
class SensorService(private val sensorRepository: SensorRepository) {

    fun getAllSensors(): List<Sensor> = sensorRepository.findAll()

    fun getSensorById(id: Long): Sensor? = sensorRepository.findById(id).orElse(null)

    fun createSensor(sensor: Sensor): Sensor = sensorRepository.save(sensor)

    fun updateSensor(id: Long, updatedSensor: Sensor): Sensor? {
        return sensorRepository.findById(id).map { existing ->
            existing.name = updatedSensor.name
            existing.type = updatedSensor.type
            existing.location = updatedSensor.location
            existing.unit = updatedSensor.unit
            existing.updatedAt = LocalDateTime.now()
            sensorRepository.save(existing)
        }.orElse(null)
    }

    fun updateSensorValue(id: Long, value: Double?, status: String?): Sensor? {
        return sensorRepository.findById(id).map { existing ->
            value?.let { existing.value = it }
            status?.let { existing.status = it }
            existing.updatedAt = LocalDateTime.now()
            sensorRepository.save(existing)
        }.orElse(null)
    }

    fun deleteSensor(id: Long): Boolean {
        return if (sensorRepository.existsById(id)) {
            sensorRepository.deleteById(id)
            true
        } else false
    }
}