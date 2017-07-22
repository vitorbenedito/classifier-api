package com.application;

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = "com.classifier")
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class])
class Application extends SpringBootServletInitializer {


  static void main(final String[] args) {
	   SpringApplication.run(Application.class, args)
  }

  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
    return application.sources(Application.class)
  }
}
