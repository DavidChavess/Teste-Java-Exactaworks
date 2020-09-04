package com.exactaworks.testejava.dto;

import java.time.OffsetDateTime;

public class SpentDtoNoTags {
	
	private Long id;
	private String personName;
	private String description;
	private OffsetDateTime datetime;
	private Double value;
	
	public SpentDtoNoTags() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OffsetDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(OffsetDateTime datetime) {
		this.datetime = datetime;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
