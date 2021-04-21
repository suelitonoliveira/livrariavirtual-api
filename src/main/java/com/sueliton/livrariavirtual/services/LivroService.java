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

import com.sueliton.livrariavirtual.domain.Livro;
import com.sueliton.livrariavirtual.dtos.LivroDTO;
import com.sueliton.livrariavirtual.repositories.LivroRepository;
import com.sueliton.livrariavirtual.services.exceptions.DataIntegrityException;
import com.sueliton.livrariavirtual.services.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repo;
	
	@Autowired
	private CategoriaService categoriaService;

	public List<Livro> findAll( Integer id_cat) {
		categoriaService.find(id_cat);
		return repo.findAllByCategoria(id_cat);
	}

	public Optional<Livro> find(Integer id) {
		Optional<Livro> obj = repo.findById(id);
		/*
		 * if (obj.isEmpty()) { throw new ObjectNotFoundException("Not Found"); }
		 */
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrato! Id: " + id + ", Tipo: " + Livro.class.getName())));
	}

	public Page<Livro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Transactional
	public Livro insert(Livro obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	@Transactional
	public Livro update(Integer id, LivroDTO objDto) {
		Optional<Livro> obj = find(id);
		obj.get().setTitulo(objDto.getTitulo());
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
