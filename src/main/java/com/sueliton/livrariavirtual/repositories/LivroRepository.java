package com.sueliton.livrariavirtual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sueliton.livrariavirtual.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
