package com.exactaworks.testejava.builder;

import java.time.OffsetDateTime;
import java.util.Set;

import com.exactaworks.testejava.dto.SpentDto;

public class SpentDtoBuilder {
	
	private SpentDto spentDto;
	
	private SpentDtoBuilder() {}
	
	public static SpentDtoBuilder oneSpentDto(){
		SpentDtoBuilder builder = new SpentDtoBuilder();
		builder.spentDto = new SpentDto();
		builder.spentDto.setId(1l);
		builder.spentDto.setPersonName("Fulano que n�o vai dar erro");
		builder.spentDto.setDescription("Gasto 1 que n�o vai dar erro");
		builder.spentDto.setValue(9.99);
		builder.spentDto.setTags(Set.of("Tag 1", "Tag 2","Tag 3"));
		return builder;
	}
	
	public SpentDtoBuilder withId(Long id){
		this.spentDto.setId(id);
		return this;
	}
	
	public SpentDtoBuilder withPersonName(String name){
		this.spentDto.setPersonName(name);
		return this;
	}
	public SpentDtoBuilder withDescription(String desc){
		this.spentDto.setDescription(desc);
		return this;
	}
	public SpentDtoBuilder withValue(Double value){
		this.spentDto.setValue(value);
		return this;
	}
	public SpentDtoBuilder withDateTime(OffsetDateTime datetime){
		this.spentDto.setDatetime(datetime);
		return this;
	}
	public SpentDtoBuilder withTags(Set<String> tags){
		this.spentDto.setTags(tags);
		return this;
	}
	
	public SpentDto build() {
		return spentDto;
	}
}
