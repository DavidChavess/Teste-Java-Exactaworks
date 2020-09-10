package com.exactaworks.testejava.controller;

import static com.exactaworks.testejava.builder.SpentDtoBuilder.oneSpentDto;
import static com.exactaworks.testejava.builder.SpentDtoNoTagsBuilder.oneSpentDtoNoTags;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import com.exactaworks.testejava.dto.SpentDtoNoTags;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.exactaworks.testejava.dto.SpentDto;
import com.exactaworks.testejava.service.SpentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class SpentControllerTest {

	static String API = "/spents";

	@MockBean
	SpentService service;

	@Autowired
	MockMvc mvc;

	@Test
	@DisplayName("Deve criar um gasto e retornar a resposta para o client corretamente")
	public void createSpent() throws Exception {
		SpentDto spentDto = createSpentDTO();
		String json = new ObjectMapper().writeValueAsString(spentDto);
		
		SpentDto spentReturned = createSpentDTO();
		spentReturned.setId(1l);
		
		given(service.insert( any(SpentDto.class) )).willReturn(spentReturned);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		mvc.perform(request)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("id").value(spentReturned.getId()))
			.andExpect(jsonPath("personName").value(spentReturned.getPersonName()))
			.andExpect(jsonPath("description").value(spentReturned.getDescription()))
			.andExpect(jsonPath("value").value(spentReturned.getValue()))
			.andExpect(jsonPath("tags", hasSize(2)));
	}

	@Test
	@DisplayName("Deve filtar gastos por parametros na URL")
	public void findSpentTest() throws Exception {
		SpentDto filter = oneSpentDto().withPersonName("Fulano").withDescription("nov").build();

		List<SpentDtoNoTags> list = Arrays.asList(
				oneSpentDtoNoTags().withPersonName("Fulano 1").withDescription("carro novo").build(),
				oneSpentDtoNoTags().withPersonName("Fulano 2").withDescription("casa nova").build());

		given(service.find( any(SpentDto.class), any(Pageable.class) ))
				.willReturn( new PageImpl<SpentDtoNoTags>(list, PageRequest.of(0, 20), 2) );

		String queryString = String.format("?personName=%s&description=%s&page=0&size=20",
				filter.getPersonName(), filter.getDescription());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(API.concat("/filters" + queryString))
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("content", hasSize(2) ))
				.andExpect(jsonPath("totalElements").value(2))
				.andExpect(jsonPath("pageable.pageSize").value(20))
				.andExpect(jsonPath("pageable.pageNumber").value(0));
	}

	public SpentDto createSpentDTO() {
		return oneSpentDto()
				.withPersonName("Fulano com nome valido")
				.withDescription("TV 55 polegadas 4K")
				.withValue(3598.55)
				.withTags(Set.of("Tv nova", "�tima qualidade"))
				.build();
	}

}
