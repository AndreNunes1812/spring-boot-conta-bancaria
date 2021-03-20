package com.wsinservices.sysbanco.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wsinservices.sysbanco.domain.model.Pessoa;
import com.wsinservices.sysbanco.domain.repository.PessoaRepository;
import com.wsinservices.sysbanco.domain.service.CadastroPessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CadastroPessoaService cadastroPessoa;
	
	@GetMapping(produces = "application/json")
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();		
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adcionar(@Valid @RequestBody Pessoa pessoa) {
		
		return cadastroPessoa.salvar(pessoa);
	}
	

}
