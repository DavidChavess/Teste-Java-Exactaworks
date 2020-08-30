package com.exactaworks.testejava.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.exactaworks.testejava.model.Spent;

public final class SpentDto {
	
	private final Long id;
	
	@NotEmpty(message = "O campo nome da pessoa é obrigatório!")
	@Length(min = 10, max = 255, message = "O campo nome da pessoa deve ter entre 10 e 255 caracteres!")
	private final String personName;
	
	@NotEmpty(message = "O campo descrição é obrigatório!")
	@Length(min = 10, max = 255, message = "O campo descrição deve ter entre 10 e 255 caracteres!")
	private final String description;
	
	@NotNull(message = "O campo data é obrigatório")
	private final Instant datetime;
	
	@NotNull(message = "O campo valor é obrigatório")
	@Positive(message = "O campo valor não pode ser negativo!")
	private final Double value;
	
	private Set<String> tags = new HashSet<>();
	
	public SpentDto() {
		this.id = null;
		this.personName = "";
		this.description = "";
		this.datetime = null;
		this.value = null;
	}

	public SpentDto(Spent spent) {
		this.id = spent.getId();
		this.personName = spent.getPersonName();
		this.description = spent.getDescription();
		this.datetime = spent.getDatetime();
		this.value = spent.getValue();
		tags.addAll(spent.getTags());
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

	public Set<String> getTags() {
		Set<String> tagsClone = new HashSet<String>();
		tagsClone.addAll(tags);
		return tagsClone;
	}	
}
