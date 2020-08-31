package com.exactaworks.testejava.service;

import java.util.List;

import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.dto.SpentDtoNoTags;

public interface SpentService {
	
	List<SpentDtoNoTags> findAll();
	SpentDto findById(Long id);
	SpentDto insert(SpentDto spent);	
	
}
