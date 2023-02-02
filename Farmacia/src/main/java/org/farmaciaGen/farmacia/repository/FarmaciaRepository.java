package org.farmaciaGen.farmacia.repository;

import java.util.List;

import org.farmaciaGen.farmacia.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
	public List<Farmacia> findAllByNomeRemedioContainingIgnoreCase(String nomeRemedio);

}
