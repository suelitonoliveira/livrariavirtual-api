package com.sueliton.livrariavirtual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sueliton.livrariavirtual.domain.Categoria;
import com.sueliton.livrariavirtual.repositories.CategoriaRepository;
import com.sueliton.livrariavirtual.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Optional<Categoria> find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Not Found");
		}
		return obj;
	}

}
