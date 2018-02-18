package com.github.graphql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public final class Article
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int id;
	@Column( nullable = false )
	private String title;
	@Column( nullable = false )
	private String subtitle;

	Article() {
	}

	public Article( final String title, final String subtitle ) {
		this.title = title;
		this.subtitle = subtitle;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setTitle( final String title ) {
		this.title = title;
	}

	public void setSubtitle( final String subtitle ) {
		this.subtitle = subtitle;
	}
}
