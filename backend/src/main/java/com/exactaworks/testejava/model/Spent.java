package com.exactaworks.testejava.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public final class Spent implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;	
	private final String person;
	private final String description;
	private final Instant datetime;
	private final Double value;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "tags")
	@Column(name="tag")
	private Set<String> tags = new HashSet<>();
	
	public Spent() {
		this.id = null;
		this.person = "";
		this.description = "";
		this.datetime = null;
		this.value = null;
	}

	public Spent(Long id, String person, String description, Instant datetime, Double value) {
		this.id = id;
		this.person = person; 
		this.description = description;
		this.datetime = datetime;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public String getPerson() {
		return person;
	}

	public String getDescription() {
		return description;
	}

	public Double getValue() {
		return value;
	}

	public Instant getDatetime() {
		return datetime;
	}
	
	public void addTag(String tag) {
		this.tags.add(tag);
	}

	public Set<String> getTags() {
		Set<String> tagsClone = new HashSet<String>();
		tagsClone.addAll(tags);
		return tagsClone;
	}
}
