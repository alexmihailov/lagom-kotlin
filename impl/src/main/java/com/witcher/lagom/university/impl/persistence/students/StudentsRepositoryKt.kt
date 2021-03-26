package com.witcher.lagom.university.impl.persistence.students

import com.google.inject.Inject
import kotlinx.coroutines.future.await
import org.taymyr.play.repository.infrastructure.persistence.DatabaseExecutionContext
import org.taymyr.play.repository.infrastructure.persistence.JPARepository
import play.db.jpa.JPAApi
import java.time.LocalDate

class StudentsRepositoryKt @Inject constructor(jpaApi: JPAApi, context: DatabaseExecutionContext)
    : JPARepository<StudentEntityKt, Int>(jpaApi, context, StudentEntityKt::class.java) {

    override fun nextIdentity() = throw AbstractMethodError("this method shouldn't be used")

    suspend fun getStudents(
        firstName: String?,
        middleName: String?,
        lastName: String?,
        birthday: LocalDate?,
        group: String?
    ): List<StudentEntityKt> {
        var query = "select s from ${StudentEntityKt::class.java.name} s where 1=1"
        val parameters = mutableMapOf<String, Any>()
        when {
            firstName != null -> {
                query += " and s.firstName = :firstName"
                parameters["firstName"] = firstName
            }
            middleName != null -> {
                query += " and s.middleName = :middleName"
                parameters["middleName"] = middleName
            }
            lastName != null -> {
                query += " and s.lastName = :lastName"
                parameters["lastName"] = lastName
            }
            birthday != null -> {
                query += " and s.birthday = :birthday"
                parameters["birthday"] = birthday
            }
            group != null -> {
                query += " and s.group = :group"
                parameters["group"] = group
            }
        }
        return findItemsByQuery(query, parameters, StudentEntityKt::class.java)
    }

    suspend fun <T> findItemsByQuery(jpaQuery: String, parameters: Map<String, Any>, clazz: Class<T>): List<T> =
        executeRO { em ->
            val query = em.createQuery(jpaQuery, clazz)
            parameters.forEach { (key, value) -> query.setParameter(key, value) }
            query.resultList.toList()
        }.await()
}