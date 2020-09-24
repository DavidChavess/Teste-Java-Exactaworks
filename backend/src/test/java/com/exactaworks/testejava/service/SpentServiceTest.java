package com.exactaworks.testejava.service;

import com.exactaworks.testejava.builder.SpentBuilder;
import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.dto.SpentDtoNoTags;
import com.exactaworks.testejava.model.Spent;
import com.exactaworks.testejava.repository.SpentRepository;
import com.exactaworks.testejava.service.impl.SpentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static com.exactaworks.testejava.builder.SpentBuilder.oneSpent;
import static com.exactaworks.testejava.builder.SpentDtoBuilder.oneSpentDto;
import static com.exactaworks.testejava.builder.SpentDtoNoTagsBuilder.oneSpentDtoNoTags;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SpentServiceTest {

	SpentService service;

	@MockBean
	SpentRepository repository;

	@MockBean
	ModelMapper mapper;

	@BeforeEach
	public void setUp(){
		this.service = new SpentServiceImpl(repository, mapper);
	}

	@Test
	@DisplayName("Deve filtar gastos com base nos parametros")
	public void findSpentTest() {
		SpentDto filter = oneSpentDto().withPersonName("Fula").withDescription("nov").build();
		PageRequest pageRequest = PageRequest.of(0, 20);

		List<Spent> listEntity = Arrays.asList(
				oneSpent().withPersonName("Fulano 1").withDescription("carro novo").build(),
				oneSpent().withPersonName("Fulano 2").withDescription("casa nova").build());

		Page page = new PageImpl(listEntity, pageRequest, 2);

		when(repository.findAll(any(Example.class), any(PageRequest.class) )).thenReturn(page);
		when(mapper.map(filter, Spent.class))
				.thenReturn(oneSpent().withPersonName("Fula").withDescription("nov").build());

		Page<SpentDtoNoTags> result = service.find(filter, pageRequest);

		assertThat(result.getContent()).hasSize(2);
		assertThat(result.getPageable().getPageSize()).isEqualTo(20);
		assertThat(result.getPageable().getPageNumber()).isEqualTo(0);
		assertThat(result.getTotalElements()).isEqualTo(2);
	}
}
