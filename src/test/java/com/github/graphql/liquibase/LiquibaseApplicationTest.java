package com.github.graphql.liquibase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import com.github.graphql.main.Application;

public class LiquibaseApplicationTest
{
	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	@Test
	public void shouldAlwaysDropSchema() throws Exception {
		Application.main( new String[] { "--server.port=0" } );
		final String output = this.outputCapture.toString();
		assertThat( output ).contains( "Successfully acquired change log lock" )
				.contains( "Dropping Database Objects" )
				.contains( "Successfully released change log lock" );
	}
}