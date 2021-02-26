package com.stoom.enderecos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stoom.enderecos.domain.model.Endereco;
import com.stoom.enderecos.domain.repository.EnderecoRepository;

@Service
public class CadastroEnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public void excluir(Long enderecoId) {
		enderecoRepository.deleteById(enderecoId);
	}
}
