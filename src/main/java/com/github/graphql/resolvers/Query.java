package com.github.graphql.resolvers;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.graphql.model.Article;
import com.github.graphql.model.Topic;
import com.github.graphql.repository.ArticleRepository;
import com.github.graphql.service.TopicService;

@Component
public class Query
		implements
			GraphQLQueryResolver
{
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private TopicService topicService;

	@Transactional
	public List<Article> getArticles() {
		return articleRepository.findAll();
	}

	public Future<List<Topic>> getTopics() {
		return topicService.findTopics();
	}

	public Future<List<Topic>> getTopicsLike( final String query ) {
		return topicService.findTopicsLike( query );
	}
}