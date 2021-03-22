package com.wsinservices.sysbanco.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
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
	private Pessoa pessoa;
	
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.pessoaController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPessoa() {
		when(this.pessoaService.obterPesssoa(1L))
			.thenReturn(new Pessoa(1L,"PF","31482716291",7));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/pessoas/{codigo}", 4L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}

}
