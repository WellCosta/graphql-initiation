package com.github.graphql.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.graphql.model.Topic;
import com.github.graphql.scraping.WebScraper;

@Service
public class TopicService
{
	@Autowired
	private Executor asyncExecutor;
	@Autowired
	private WebScraper webScraper;

	@Value( "${scraping.request.url}" )
	private String url;
	@Value( "${scraping.baseUrl}" )
	private String baseUrl;
	@Value( "${scraping.selector}" )
	private String selector;

	public Future<List<Topic>> findTopics() {
		return startScrapeTask();
	}

	public Future<List<Topic>> findTopicsLike( final String query ) {
		Objects.requireNonNull( query );
		return startScrapeTask().thenApply( filterTopicsByQuery( query ) );
	}

	private CompletableFuture<List<Topic>> startScrapeTask() {
		final CompletableFuture<List<Topic>> future = new CompletableFuture<>();
		asyncExecutor.execute( ( ) -> {
			try {
				final Elements selection = scrapeGraphQlTopics();
				final List<Topic> topics = extractTopics( selection ).collect( Collectors.toList() );
				future.complete( topics );
			} catch ( final Exception e ) {
				future.completeExceptionally( e );
			}
		} );
		return future;
	}

	private Elements scrapeGraphQlTopics() throws IOException {
		final Document document = webScraper.scrape( url );
		return document.select( selector );
	}

	private Stream<Topic> extractTopics( final Elements selection ) {
		return selection.stream()
				.map( element -> new Topic( element.text(), baseUrl + element.attr( "href" ) ) );
	}

	private Function<List<Topic>, List<Topic>> filterTopicsByQuery( final String query ) {
		return topics -> {
			return topics.stream()
					.filter( topic -> topic.getName().contains( query ) )
					.collect( Collectors.toList() );
		};
	}
}
