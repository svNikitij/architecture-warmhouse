package ru.svn.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.svn.dao.entity.Sensor

@Repository
interface SensorRepository : JpaRepository<Sensor, Long> {
    fun findByLocation(location: String): List<Sensor>
}