package com.witcher.lagom.university.impl.infrastructure;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.witcher.lagom.university.api.UniversityService;
import com.witcher.lagom.university.impl.UniversityServiceImpl;

/**
 * The module that binds the HelloService so that it can be served.
 */
public class UniversityModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    bindService(UniversityService.class, UniversityServiceImpl.class);
  }
}
