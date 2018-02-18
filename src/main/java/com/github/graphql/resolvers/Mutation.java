package com.github.graphql.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.github.graphql.model.Article;
import com.github.graphql.model.ArticleNotFoundException;
import com.github.graphql.repository.ArticleRepository;

@Component
public class Mutation
		implements
			GraphQLMutationResolver
{
	@Autowired
	private ArticleRepository articleRepository;

	@Transactional
	public Article createArticle( final String title, final String subtitle ) {
		final Article entity = new Article( title, subtitle );
		return articleRepository.save( entity );
	}

	@Transactional
	public Article updateArticle( final Integer id, final String title, final String subtitle ) {
		final Article article = articleRepository.findOne( id );
		if ( article == null ) {
			throw new ArticleNotFoundException();
		}
		article.setTitle( title );
		article.setSubtitle( subtitle );
		return articleRepository.save( article );
	}
}