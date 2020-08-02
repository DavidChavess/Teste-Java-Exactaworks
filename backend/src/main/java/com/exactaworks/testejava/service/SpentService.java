package com.exactaworks.testejava.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.repository.SpentRepository;

@Service
public class SpentService implements ISpentService {
	
	@Autowired
	private SpentRepository repository;

	@Override
	public List<SpentDto> findAll() {
		return repository.findAll().stream().map(SpentDto::new).collect(Collectors.toList());
	}

	@Override
	public SpentDto findById(Long id) {
		return null;
	}

	@Override
	public SpentDto insert(SpentDto spent) {
		return null;
	}
}
