package org.farmaciaGen.farmacia.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.farmaciaGen.farmacia.model.Farmacia;
import org.farmaciaGen.farmacia.repository.CategoriaRepository;
import org.farmaciaGen.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController // para definir que é uma classe controller
@RequestMapping("/farmacia")
@CrossOrigin(origins = " * ", allowedHeaders = " * ") // define que ira aceitar requisicoes de qualquer origem
public class FarmaciaController {
	@Autowired // define que o proprio spring instancie os objetos
	private FarmaciaRepository repository;
	
	@Autowired // define que o proprio spring instancie os objetos
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("/titulo")
	public ResponseEntity<List<Farmacia>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") // busca tudo pelo ID
	public ResponseEntity<Farmacia> getById(@PathVariable Long id) {
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/titulo/{titulo}") // Busca tudo pelo Titulo
	public ResponseEntity<List<Farmacia>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByNomeRemedioContainingIgnoreCase(titulo));
	}
	
	@PostMapping // Insere novos Dados
	public ResponseEntity<Farmacia> post(@Valid @RequestBody Farmacia farmacia) {
		if (categoriaRepository.existsById(farmacia.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(farmacia));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping // Atualiza os dados que contem no banco de dados
	public ResponseEntity<Farmacia> put(@Valid @RequestBody Farmacia farmacia) {
		if (repository.existsById(farmacia.getId())) {
			if (categoriaRepository.existsById(farmacia.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(repository.save(farmacia));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {

		Optional<Farmacia> farmacia = repository.findById(id);

		if (farmacia.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		repository.deleteById(id);
	}

}
