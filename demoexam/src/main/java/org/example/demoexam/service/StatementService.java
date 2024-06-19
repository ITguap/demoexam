package org.example.demoexam.service;

import lombok.RequiredArgsConstructor;
import org.example.demoexam.model.Statement;
import org.example.demoexam.model.Status;
import org.example.demoexam.model.User;
import org.example.demoexam.repo.StatementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatementService {

    private final StatementRepository statementRepository;

    public List<Statement> getAll() {
        return statementRepository.findAll();
    }

    public List<Statement> getAllByAuthorId(Long id) {
        return statementRepository.findAllByAuthorId(id);
    }

    public void save(Statement statement, User user) {
        statement.setStatus(Status.NEW);
        statement.setAuthor(user);
        statementRepository.save(statement);
    }

    public void delete(Long id){
        this.statementRepository.deleteById(id);
    }

    public void reject(Long id) {
        Statement statement = statementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заявление не найдено"));
        statement.setStatus(Status.REJECTED);
        statementRepository.save(statement);
    }

    public void confirm(Long id) {
        Statement statement = statementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заявление не найдено"));
        statement.setStatus(Status.CONFIRMED);
        statementRepository.save(statement);
    }

}
