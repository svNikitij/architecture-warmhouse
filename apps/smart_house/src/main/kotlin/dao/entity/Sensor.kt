package ru.svn.dao.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "sensors")
data class Sensor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    var name: String? = null,
    @Column
    var type: String? = null,
    @Column
    var location: String? = null,
    @Column
    var unit: String? = null,

    @Column(name = "current_value")
    var value: Double? = null,

    @Column
    var status: String? = "active",

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
)