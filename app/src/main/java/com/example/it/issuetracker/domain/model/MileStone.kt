package com.example.it.issuetracker.domain.model

import java.io.Serializable

data class MileStone(val id: Long, val name: String, val deadLine: String) : Serializable