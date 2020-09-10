package com.exactaworks.testejava.builder;

import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.dto.SpentDtoNoTags;

import java.util.Set;

public class SpentDtoNoTagsBuilder {
	
	private SpentDtoNoTags spentDtoNoTags;
	
	private SpentDtoNoTagsBuilder() {}
	
	public static SpentDtoNoTagsBuilder oneSpentDtoNoTags(){
		SpentDtoNoTagsBuilder builder = new SpentDtoNoTagsBuilder();
		builder.spentDtoNoTags = new SpentDtoNoTags();
		builder.spentDtoNoTags.setId(1l);
		builder.spentDtoNoTags.setPersonName("Fulano que n�o vai dar erro");
		builder.spentDtoNoTags.setDescription("Gasto 1 que n�o vai dar erro");
		builder.spentDtoNoTags.setValue(9.99);
		return builder;
	}

	public SpentDtoNoTagsBuilder withPersonName(String name){
		this.spentDtoNoTags.setPersonName(name);
		return this;
	}
	public SpentDtoNoTagsBuilder withDescription(String desc){
		this.spentDtoNoTags.setDescription(desc);
		return this;
	}

	public SpentDtoNoTags build() {
		return spentDtoNoTags;
	}
}
