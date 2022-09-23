package br.com.treinamento.transactions.repository;

import br.com.treinamento.transactions.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
