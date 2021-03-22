package com.wsinservices.sysbanco.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.wsinservices.sysbanco.api.controller.PessoaController;
import com.wsinservices.sysbanco.domain.model.Pessoa;
import com.wsinservices.sysbanco.domain.repository.ContaCorrenteRepository;
import com.wsinservices.sysbanco.domain.repository.PessoaRepository;
import com.wsinservices.sysbanco.domain.service.CadastroPessoaService;
import com.wsinservices.sysbanco.domain.service.ContaCorrenteService;

import io.restassured.http.ContentType;

@WebMvcTest
public class PessoaControllerTest {
	
	@Autowired
	private PessoaController pessoaController;
	
	@MockBean
	private CadastroPessoaService pessoaService;
	
	@MockBean
	public ContaCorrenteService contaCorrenteService;
	
	@MockBean
	private ContaCorrenteRepository contaRepository;
	
	
	@MockBean
	private PessoaRepository pessoaRepository;
	
	@MockBean
	private Pessoa pessoa;
	
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.pessoaController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPessoa() {
		when(this.pessoaService.obterPesssoa(4L))
			.thenReturn(new Pessoa(4L,"PF","31482716291",7));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/pessoas/{codigo}", 4L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarPessoa() {
		
		when(this.pessoaService.obterPesssoa(100L))
			.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/filmes/{codigo}", 5L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoBuscarPessoa() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/pessoas/{codigo}", -1L)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
		verify(this.pessoaService, never()).obterPesssoa(-1L);
	}

}
