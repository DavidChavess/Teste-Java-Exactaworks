package com.exactaworks.testejava.controller;

import static com.exactaworks.testejava.builder.SpentDtoBuilder.oneSpentDto;
import static com.exactaworks.testejava.builder.SpentDtoNoTagsBuilder.oneSpentDtoNoTags;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.never;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.exactaworks.testejava.builder.SpentDtoNoTagsBuilder;
import com.exactaworks.testejava.dto.SpentDtoNoTags;
import com.exactaworks.testejava.exception.ObjectNotFoundException;
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
	@DisplayName("Deve criar um gasto com sucesso")
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
	@DisplayName("Não deve criar um gasto se existir erros de validação")
	public void invalidSpentTest() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new SpentDto());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);

		mvc.perform(request)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("errors", hasSize(3)))
				.andExpect(jsonPath("statuscode").value(400));

		verify(service, never()).insert(any(SpentDto.class));
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

	@Test
	@DisplayName("Deve retornar todos os gastos")
	public void findAllSpentTest() throws Exception {
		List<SpentDtoNoTags> listSpent = Arrays.asList(
				SpentDtoNoTagsBuilder.oneSpentDtoNoTags().withId(1l).build(),
				SpentDtoNoTagsBuilder.oneSpentDtoNoTags().withId(2l).build());

		given(service.findAll()).willReturn(listSpent);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(API)
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("[0].id").value(1l))
				.andExpect(jsonPath("[1].id").value(2l));
	}

	@Test
	@DisplayName("Deve retornar um gasto com o id informado")
	public void findSpentById() throws Exception {
		Long id = 1l;
		SpentDto spentReturned = createSpentDTO();
		spentReturned.setId(1l);
		given(service.findById(id)).willReturn(spentReturned);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(API.concat("/"+id))
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").value(id))
				.andExpect(jsonPath("personName").value(spentReturned.getPersonName()))
				.andExpect(jsonPath("description").value(spentReturned.getDescription()))
				.andExpect(jsonPath("value").value(spentReturned.getValue()))
				.andExpect(jsonPath("tags", hasSize(2)));
	}

	@Test
	@DisplayName("Deve lançar erro de gasto não encontrado se não existir um gasto com o id informado")
	public void findNotFouldSpentById() throws Exception {
		Long id = 1l;
		String messageError = "Gasto não encontrado, id= " + id;
		given(service.findById(id)).willThrow(new ObjectNotFoundException(messageError));

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(API.concat("/"+id))
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(request)
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("errors[0]").value(messageError));
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
