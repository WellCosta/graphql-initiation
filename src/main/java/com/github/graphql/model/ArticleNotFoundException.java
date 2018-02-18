package com.github.graphql.model;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;

public class ArticleNotFoundException
		extends
			RuntimeException
		implements
			GraphQLError
{
	private static final long serialVersionUID = 1L;

	@Override
	public List<SourceLocation> getLocations() {
		return Collections.emptyList();
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.DataFetchingException;
	}
}
