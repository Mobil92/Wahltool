package de.bbqesports.wahltool.app;

import javax.annotation.PostConstruct;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.bbqesports.wahltool.db.TestDaten;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "de.bbqesports.wahltool.db")
@EntityScan(basePackages = "de.bbqesports.wahltool.db")
@ComponentScan({ "de.bbqesports.wahltool.*" })
@EnableScheduling
public class WahltoolApplication {

	@Autowired
	private TestDaten testDaten;

	public static void main(String[] args) {
		SpringApplication.run(WahltoolApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	@PostConstruct
	private void setupTestDaten() {
		testDaten.setupTestData();
	}
}
