package com.sueliton.livrariavirtual.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sueliton.livrariavirtual.domain.Categoria;
import com.sueliton.livrariavirtual.dtos.CategoriaDTO;
import com.sueliton.livrariavirtual.repositories.CategoriaRepository;
import com.sueliton.livrariavirtual.services.exceptions.DataIntegrityException;
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
		/*
		 * if (obj.isEmpty()) { throw new ObjectNotFoundException("Not Found"); }
		 */
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrato! Id: " + id + ", Tipo: " + Categoria.class.getName())));
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Transactional
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	@Transactional
	public Categoria update(Integer id, CategoriaDTO objDto) {
		Optional<Categoria> obj = find(id);
		obj.get().setNome(objDto.getNome());
		obj.get().setDescricao(objDto.getDescricao());
		return repo.save(obj.get());
	}

	@Transactional
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (ConstraintViolationException e) {
			throw new DataIntegrityException("Exclusão não permitida, itens vinculados!");
		}

	}

}
