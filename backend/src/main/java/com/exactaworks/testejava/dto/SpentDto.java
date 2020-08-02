package com.exactaworks.testejava.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.exactaworks.testejava.model.Spent;

public class SpentDto {
	private Long id;
	private String person;
	private String description;
	private Instant datetime;
	private Double value;
	private Set<String> tags = new HashSet<>();
	
	public SpentDto() {}

	public SpentDto(Spent spent) {
		this.id = spent.getId();
		this.person = spent.getPerson();
		this.description = spent.getDescription();
		this.datetime = spent.getDatetime();
		this.value = spent.getValue();
		spent.getTags().stream().forEach(tags::add);
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

	public Instant getDatetime() {
		return datetime;
	}

	public void setDatetime(Instant datetime) {
		this.datetime = datetime;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Set<String> getTags() {
		return tags;
	}	
}
