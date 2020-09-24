package com.exactaworks.testejava.service;

import java.util.List;

import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.dto.SpentDtoNoTags;
import com.exactaworks.testejava.model.Spent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpentService {
	
	List<SpentDtoNoTags> findAll();

	SpentDto findById(Long id);

	SpentDto insert(SpentDto spent);

    Page<SpentDtoNoTags> find(SpentDto spentDto, Pageable pageRequest);
}
