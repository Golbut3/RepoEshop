package eshop.formation.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import eshop.formation.config.AppConfig;

public class SpringApplication {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ctx.getBeanFactory().createBean(PopulateTest.class).run();
		ctx.close();
	}

}
