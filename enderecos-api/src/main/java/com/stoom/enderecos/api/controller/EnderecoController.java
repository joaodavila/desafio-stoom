package com.stoom.enderecos.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stoom.enderecos.domain.model.Endereco;
import com.stoom.enderecos.domain.repository.EnderecoRepository;
import com.stoom.enderecos.domain.service.CadastroEnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CadastroEnderecoService cadastroEndereco;
	
	@GetMapping
	public List<Endereco> listAll() {
		
		return enderecoRepository.findAll(); 
	}
	
	@GetMapping("/{enderecoId}")
	public ResponseEntity<Endereco> buscar(@PathVariable Long enderecoId){
		Optional<Endereco> endereco = enderecoRepository.findById(enderecoId);
		
		if(endereco.isPresent()) {
			return ResponseEntity.ok(endereco.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco adicionar(@Valid @RequestBody Endereco endereco) {
		return cadastroEndereco.salvar(endereco);
	}

	@PutMapping("/{enderecoId}")
	public ResponseEntity<Endereco> atualizar(@Valid @PathVariable Long enderecoId, @RequestBody Endereco endereco){
		if(!enderecoRepository.existsById(enderecoId)) {
			return ResponseEntity.notFound().build();
		}
		
		endereco.setId(enderecoId);
		endereco = cadastroEndereco.salvar(endereco);
		
		return ResponseEntity.ok(endereco);
	}
	
	@DeleteMapping("/{enderecoId}")
	public ResponseEntity<Void> excluir(@PathVariable Long enderecoId){
		if(!enderecoRepository.existsById(enderecoId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroEndereco.excluir(enderecoId);
		
		return ResponseEntity.noContent().build();
	}
	
}
