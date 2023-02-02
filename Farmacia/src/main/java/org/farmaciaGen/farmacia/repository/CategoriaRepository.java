package org.farmaciaGen.farmacia.repository;


import java.util.List;


import org.farmaciaGen.farmacia.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public List<Categoria> findAllByDescricaoContainingIgnoreCase
	(@Param("descricao") String descricao);

}
