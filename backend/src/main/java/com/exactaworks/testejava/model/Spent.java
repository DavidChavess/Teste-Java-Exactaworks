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
public class Spent implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String person;
	private String description;
	private Instant datetime;
	private Double value;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "tags")
	@Column(name="tag")
	private Set<String> tags = new HashSet<>();
	
	public Spent() {}

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

	public void setId(Long id) {
		this.id = id;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	public Instant getDatetime() {
		return datetime;
	}

	public void setDatetime(Instant datetime) {
		this.datetime = datetime;
	}

	public Set<String> getTags() {
		return tags;
	}
}
