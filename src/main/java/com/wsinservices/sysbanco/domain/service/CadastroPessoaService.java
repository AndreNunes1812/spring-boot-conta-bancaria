package com.wsinservices.sysbanco.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsinservices.sysbanco.domain.exception.NegocioException;
import com.wsinservices.sysbanco.domain.model.Pessoa;
import com.wsinservices.sysbanco.domain.repository.PessoaRepository;

@Service
public class CadastroPessoaService {
	
	@Autowired
	public PessoaRepository pessoaRepository;
	
	@Autowired
	public ContaCorrenteService contaCorrenteService;

	
	public Pessoa salvar(Pessoa pessoa) {
		
		Pessoa pessoaExistente = pessoaRepository.findByDocumento(pessoa.getDocumento());
		
		if(pessoaExistente != null && !pessoaExistente.equals(pessoa)) {
			throw new NegocioException("Já existe uma pessoa cadasatrada com esté documento.");
		}
		
		pessoa.setScore( 0 + (int)(Math.random() * ((9 - 0) + 1)));
				
		
		var	pessoaSalva = pessoaRepository.save(pessoa);
		
		
		contaCorrenteService.criar(pessoaSalva);
		
		return pessoaSalva ; 
	}
	
	public Pessoa obterPesssoa(Long codigo) {
		
		Pessoa pessoaObtida = pessoaRepository.findById(codigo).get();
		
		if (pessoaObtida == null) {
			throw new NegocioException("Pessoa codigo codigo:"+codigo+" não está disponivel na base de dados.");
		}
		
		return pessoaObtida;
	}
	
}


