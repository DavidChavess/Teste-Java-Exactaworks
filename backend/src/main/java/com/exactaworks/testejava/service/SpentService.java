package com.exactaworks.testejava.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.dto.SpentDtoNoTags;
import com.exactaworks.testejava.exception.ObjectNotFoundException;
import com.exactaworks.testejava.model.Spent;
import com.exactaworks.testejava.repository.SpentRepository;

@Service
public class SpentService implements ISpentService {

	@Autowired
	private SpentRepository repository;

	@Override
	public List<SpentDtoNoTags> findAll() {
		return repository.findAll().stream().map(SpentDtoNoTags::new).collect(Collectors.toList());
	}

	@Override
	public SpentDto findById(Long id) {
		Optional<Spent> spent = repository.findById(id);
		if (spent.isPresent()) {
			return fromDto(spent.get());
		}
		throw new ObjectNotFoundException("Gasto não encontrado, id= " + id);
	}

	@Override
	public SpentDto insert(SpentDto spentDto) {
		return fromDto(repository.save(toDto(spentDto)));
	}

	private SpentDto fromDto(Spent spent) {
		return new SpentDto(spent);
	}
	
	private Spent toDto(SpentDto spentDto) {
		Spent spent = new Spent(null, spentDto.getPerson(),spentDto.getDescription(),spentDto.getDatetime(),spentDto.getValue());
		spentDto.getTags().stream().forEach(spent::addTag);
		return spent;		
	}	
}
