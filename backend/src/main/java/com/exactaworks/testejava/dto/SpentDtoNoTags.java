package com.exactaworks.testejava.dto;

import java.time.Instant;

import com.exactaworks.testejava.model.Spent;

public final class SpentDtoNoTags {
	
	private final Long id;
	private final String personName;
	private final String description;
	private final Instant datetime;
	private final Double value;
	
	public SpentDtoNoTags() {
		this.id = null;
		this.personName = "";
		this.description = "";
		this.datetime = null;
		this.value = null;
	}

	public SpentDtoNoTags(Spent spent) {
		this.id = spent.getId();
		this.personName = spent.getPersonName();
		this.description = spent.getDescription();
		this.datetime = spent.getDatetime();
		this.value = spent.getValue();
	}
	
	public Long getId() {
		return id;
	}

	public String getPersonName() {
		return personName;
	}

	public String getDescription() {
		return description;
	}

	public Instant getDatetime() {
		return datetime;
	}

	public Double getValue() {
		return value;
	}
}