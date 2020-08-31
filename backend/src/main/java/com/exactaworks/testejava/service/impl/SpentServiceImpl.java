package com.exactaworks.testejava.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.dto.SpentDtoNoTags;
import com.exactaworks.testejava.exception.ObjectNotFoundException;
import com.exactaworks.testejava.model.Spent;
import com.exactaworks.testejava.repository.SpentRepository;
import com.exactaworks.testejava.service.SpentService;

@Service
public class SpentServiceImpl implements SpentService {

	@Autowired
	private SpentRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<SpentDtoNoTags> findAll() {
		return repository.findAll()
				.stream()
				.map(spent -> mapper.map(spent, SpentDtoNoTags.class))
				.collect(Collectors.toList());
	}

	@Override
	public SpentDto findById(Long id) {
		return repository.findById(id)
				.map(spent -> mapper.map(spent, SpentDto.class))
				.orElseThrow(() -> new ObjectNotFoundException("Gasto não encontrado, id= " + id));
	}

	@Override
	public SpentDto insert(SpentDto spentDto) {
		spentDto.setId(null);
		Spent spent = repository.save(mapper.map(spentDto, Spent.class));
		return mapper.map(spent, SpentDto.class);
	}

}
