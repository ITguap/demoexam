package org.example.demoexam.repo;

import org.example.demoexam.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatementRepository extends JpaRepository<Statement, Long> {

    List<Statement> findAllByAuthorId(Long id);

}
