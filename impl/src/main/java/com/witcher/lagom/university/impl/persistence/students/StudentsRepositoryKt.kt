package com.witcher.lagom.university.impl.persistence.students

import com.google.inject.Inject
import kotlinx.coroutines.future.await
import org.taymyr.play.repository.infrastructure.persistence.DatabaseExecutionContext
import org.taymyr.play.repository.infrastructure.persistence.JPARepository
import play.db.jpa.JPAApi
import java.time.LocalDate
import javax.persistence.EntityManager

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
        return findItemsByQuery(query, parameters)
    }

    suspend inline fun <reified T> findItemsByQuery(jpaQuery: String, parameters: Map<String, Any>): List<T> =
        bridgeEm { em ->
            val query = em.createQuery(jpaQuery, T::class.java)
            parameters.forEach { (key, value) -> query.setParameter(key, value) }
            query.resultList.toList()
        }.await()

    @PublishedApi
    internal fun <T> bridgeEm(function: (EntityManager) -> List<T>) = executeRO(function)
}