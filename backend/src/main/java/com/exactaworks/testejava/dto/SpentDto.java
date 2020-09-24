package com.exactaworks.testejava.dto;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class SpentDto {
	
	private Long id;
	
	@NotEmpty(message = "O campo nome da pessoa é obrigatório!")
	@Length(min = 10, max = 255, message = "O campo nome da pessoa deve ter entre 10 e 255 caracteres!")
	private String personName;
	
	@NotEmpty(message = "O campo descrição é obrigatório!")
	@Length(min = 10, max = 255, message = "O campo descrição deve ter entre 10 e 255 caracteres!")
	private String description;
	
	private OffsetDateTime datetime;
	
	@NotNull(message = "O campo valor é obrigatório")
	@Positive(message = "O campo valor não pode ser negativo!")
	private Double value;
	
	private Set<String> tags = new HashSet<>();
	
	public SpentDto() {}

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

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
}
