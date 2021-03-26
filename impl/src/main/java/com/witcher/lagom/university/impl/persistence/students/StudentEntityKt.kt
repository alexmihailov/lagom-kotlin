package com.witcher.lagom.university.impl.persistence.students

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "students")
data class StudentEntityKt(

    @Id
    val id: Int = 0,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "middle_name")
    val middleName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column
    val birthday: LocalDate,

    @Column(name = "student_group")
    val group: String,
)
