package com.exactaworks.testejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.service.SpentService;


@RestController
@RequestMapping("/spents")
public class SpentController {
	
	@Autowired
	private SpentService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<SpentDto> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SpentDto findById(@PathVariable Long id){
		return service.findById(id);
	}	
}
