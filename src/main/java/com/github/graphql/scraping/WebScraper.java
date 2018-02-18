package com.github.graphql.scraping;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WebScraper
{
	@Value( "${scraping.request.timeoutInMillis:30000}" )
	private Integer timeoutInMillis;

	public Document scrape( final String url ) throws IOException {
		return Jsoup.connect( url ).timeout( timeoutInMillis ).get();
	}
}
