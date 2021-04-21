package com.sueliton.livrariavirtual.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sueliton.livrariavirtual.domain.Categoria;
import com.sueliton.livrariavirtual.domain.Livro;
import com.sueliton.livrariavirtual.repositories.CategoriaRepository;
import com.sueliton.livrariavirtual.repositories.LivroRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;
	
	public void instanciaBaseDeDados() {
		Categoria cat1 = new Categoria(null, "informática", "Livros de TI");
		Categoria cat2 = new Categoria(null, "Ficção Científica", " Livros de Ficção Científica");
		Categoria cat3 = new Categoria(null, "Biografias", "Livros de Biografias");
		Categoria cat4 = new Categoria(null, "Gramática", "Livros de gramática");
		Categoria cat5 = new Categoria(null, "Gramática", "Livros de gramática");
		
		
		Livro l1 = new Livro(null, "Clean code", "Robert Martin", "Lorem ipsum", cat1);
		Livro l2 = new Livro(null, "Engenharia de software", "Louis V. Gerstner", "Lorem ipsum", cat1);
		Livro l3 = new Livro(null, "The Time Machine", "H.G. Wells", "Lorem ipsum", cat2);
		Livro l4 = new Livro(null, "The War of the Worlds", "H.G. Wells", "Lorem ipsum", cat3);
		Livro l5 = new Livro(null, "Nova gramática", "Lindley Cintra", "Lorem ipsum", cat4);
		
		cat1.getLivros().addAll(Arrays.asList(l1,l2));
		cat2.getLivros().addAll(Arrays.asList(l3));
		cat3.getLivros().addAll(Arrays.asList(l4));
		cat4.getLivros().addAll(Arrays.asList(l5));
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		this.livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
	}

}
