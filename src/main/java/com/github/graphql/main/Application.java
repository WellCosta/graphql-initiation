package com.github.graphql.main;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.github.graphql.model.ModelPackageScan;
import com.github.graphql.repository.RepositoriesPackageScan;
import com.github.graphql.resolvers.ResolversPackageScan;
import com.github.graphql.scraping.ScrapingPackageScan;
import com.github.graphql.service.ServicePackageScan;

@SpringBootApplication( scanBasePackageClasses = {
		ResolversPackageScan.class,
		Application.class,
		ScrapingPackageScan.class,
		ServicePackageScan.class
} )
@EntityScan( basePackageClasses = {
		ModelPackageScan.class
} )
@EnableJpaRepositories( basePackageClasses = {
		RepositoriesPackageScan.class
} )
public class Application
{
	public static void main( final String[] args ) throws Exception {
		SpringApplication.run( Application.class, args );
	}

	@Bean
	public Executor asyncExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize( 2 );
		executor.setMaxPoolSize( 2 );
		executor.setQueueCapacity( 500 );
		executor.setThreadNamePrefix( "com.github.asyn.services-" );
		executor.initialize();
		return executor;
	}
}