package com.witcher.lagom.university.impl.converters

import com.witcher.lagom.university.api.data.StudentDataKt
import com.witcher.lagom.university.impl.persistence.students.StudentEntityKt

fun StudentEntityKt.toStudentDataKt() = StudentDataKt(
    firstName = this.firstName, middleName = this.middleName,
    lastName = this.lastName, birthday = this.birthday, group = this.group
)
