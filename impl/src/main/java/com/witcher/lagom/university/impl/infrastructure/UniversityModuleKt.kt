package com.witcher.lagom.university.impl.infrastructure

import com.google.inject.AbstractModule
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport
import com.witcher.lagom.university.api.UniversityServiceKt
import com.witcher.lagom.university.impl.UniversityServiceImplKt

class UniversityModuleKt : AbstractModule(), ServiceGuiceSupport  {

    override fun configure() {
        bindService(UniversityServiceKt::class.java, UniversityServiceImplKt::class.java)
    }
}
