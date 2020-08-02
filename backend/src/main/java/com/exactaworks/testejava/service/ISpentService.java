package com.exactaworks.testejava.service;

import java.util.List;

import com.exactaworks.testejava.dto.SpentDto;

public interface ISpentService {
	
	List<SpentDto> findAll();
	SpentDto findById(Long id);
	SpentDto insert(SpentDto spent);	
	
}
