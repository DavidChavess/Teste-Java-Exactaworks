package com.exactaworks.testejava.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exactaworks.testejava.controller.exceptionHandler.StandardError;
import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.dto.SpentDtoNoTags;
import com.exactaworks.testejava.service.SpentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/spents")
public class SpentController {
	
	private SpentService service;

	public SpentController(SpentService service){
		this.service = service;
	}

	@ApiOperation(value = "Retorna todos os gastos")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<SpentDtoNoTags> findAll(){
		return service.findAll();
	}

	@GetMapping(value = "/filters")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Retorna todos os gastos filtrados por parametros na url")
	@ApiResponses(value = @ApiResponse(code = 200, message = "gastos paginados", response = Page.class))
	public Page<SpentDtoNoTags> find(SpentDto spentDto, Pageable pageRequest){
		return service.find(spentDto, pageRequest);
	}

	@ApiOperation(value = "Retorna um gasto por id")
	@ApiResponses(value = @ApiResponse(code = 404, message = "Gasto n�o encontrado", response = StandardError.class))
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SpentDto findById(@PathVariable Long id){
		return service.findById(id);
	}	
	
	@ApiOperation(value = "Insere um gasto")
	@ApiResponses(value = @ApiResponse(code = 400, message = "Erro de valida��o", response = StandardError.class))
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SpentDto insert(@RequestBody @Valid SpentDto spentDto) {
		return service.insert(spentDto);
	}
	
}
