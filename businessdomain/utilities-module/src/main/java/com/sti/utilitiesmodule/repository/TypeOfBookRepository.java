package com.sti.utilitiesmodule.repository;

import com.sti.utilitiesmodule.model.TypeOfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for TypeOfBook entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface TypeOfBookRepository extends JpaRepository<TypeOfBook, String> {
}
