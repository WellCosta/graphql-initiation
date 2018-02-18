package com.github.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.graphql.model.Article;

public interface ArticleRepository
		extends
			JpaRepository<Article, Integer>
{

}
