package br.com.rafael.accessviewerjava.repository;

import br.com.rafael.accessviewerjava.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
