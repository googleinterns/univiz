package com.google.univiz;

import static com.google.common.truth.Truth.assertThat;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.reflections.Reflections;

@RunWith(JUnit4.class)
public final class UnivizBootstrapModuleTest {

  @Test
  public void testAllWebServletsCanLoad() {
    Injector injector = Guice.createInjector(new UnivizBootstrapModule());
    Reflections reflections = new Reflections("com.google.univiz");
    Set<Class<?>> existingServletClasses = reflections.getTypesAnnotatedWith(WebServlet.class);

    assertThat(existingServletClasses).isNotEmpty();
    for (Class<?> servletClass : existingServletClasses) {
      Object servlet = injector.getInstance(servletClass);
      assertThat(servlet).isNotNull();
    }
  }
}
