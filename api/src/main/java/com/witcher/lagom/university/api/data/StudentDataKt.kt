package com.witcher.lagom.university.api.data

import java.time.LocalDate

data class StudentDataKt(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val birthday: LocalDate,
    val group: String
)
