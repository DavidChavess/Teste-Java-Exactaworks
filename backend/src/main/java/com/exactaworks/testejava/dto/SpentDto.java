package com.exactaworks.testejava.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.exactaworks.testejava.model.Spent;

public class SpentDto {
	
	private Long id;
	
	@NotEmpty(message = "O campo nome da pessoa é obrigatório!")
	@Length(min = 5, max = 80, message = "O campo nome deve ter entre 5 e 80 caracteres!")
	private String person;
	
	@NotEmpty(message = "O campo descrição é obrigatório!")
	@Length(min = 3, max = 255, message = "O campo descrição deve ter entre 5 e 255 caracteres!")
	private String description;
	
	@NotNull(message = "O campo data é obrigatório")
	private Instant datetime;
	
	@Positive(message = "O preço não pode ser negativo!")
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
