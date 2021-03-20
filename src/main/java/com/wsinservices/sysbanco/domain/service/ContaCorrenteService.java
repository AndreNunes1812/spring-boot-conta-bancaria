package com.wsinservices.sysbanco.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wsinservices.sysbanco.domain.model.ContaCorrente;
import com.wsinservices.sysbanco.domain.model.Pessoa;
import com.wsinservices.sysbanco.domain.repository.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {
	
	@Autowired
	private ContaCorrenteRepository contaRepository;
	
		
	@Value("${codigo.agencia}")
	private String codigoAgencia;	
	
	public List<ContaCorrente> buscar() {
		
		return contaRepository.findAll();	
		
	}
	
	public ContaCorrente criar(Pessoa pessoa) {
		
		var numero = String.format("%06d", (int)(Math.random() * (30)* 10000));
		
		
		var contaCorrente = new ContaCorrente();
		
		contaCorrente.setNumero(numero);
		contaCorrente.setAgencia(codigoAgencia);
		contaCorrente.setPessoa(pessoa);
				
		
		if(pessoa.getTipo().equals("PF")){
		   contaCorrente.setTipo("C");				
		} else {
		  contaCorrente.setTipo("E");
		}
		
		validarLimiteCartao(contaCorrente, pessoa.getScore());
		
		contaRepository.save(contaCorrente);
						
		return contaRepository.save(contaCorrente);
	}
	
	public void validarLimiteCartao(ContaCorrente contacorrente, int score ) {

		if(score < 2) {
			contacorrente.setLimite(0.00);
			contacorrente.setCartao(0.00);
		}
		if((score >= 2) && (score <=5)) {
			contacorrente.setLimite(1000.00);
			contacorrente.setCartao(200.00);
		}
		
		if((score >= 6) && (score <=8)) {
			contacorrente.setLimite(2000.00);
			contacorrente.setCartao(2000.00);
		}
		
		if((score >= 6) && (score <=8)) {
			contacorrente.setLimite(2000.00);
			contacorrente.setCartao(2000.00);
		}
		
		if(score == 9) {
			contacorrente.setLimite(5000.00);
			contacorrente.setCartao(15000.00);
		}
		
	}
	

}
