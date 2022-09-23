package br.com.treinamento.transactions.service;

import br.com.treinamento.transactions.adapters.TransactionCopyAdapter;
import br.com.treinamento.transactions.domain.TransactionCopy;
import br.com.treinamento.transactions.dto.TransactionDTO;
import br.com.treinamento.transactions.exception.SQLException;
import br.com.treinamento.transactions.repository.TransactionCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionCopyService {

    @Autowired
    TransactionCopyRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionDTO createNew(final TransactionDTO dto) {
        final TransactionCopy transaction = repository.save(TransactionCopyAdapter.toDomain(dto));
        return TransactionCopyAdapter.toDto(transaction);
    }

    @Transactional
    public TransactionDTO getParallel(final TransactionDTO dto) {
        final TransactionCopy copy = repository.findById(dto.getId())
                .orElseThrow(() -> new SQLException("Nenhum registro encontrado."));

        return TransactionCopyAdapter.toDto(copy);
    }

    @Transactional
    public TransactionDTO createParallel(final TransactionDTO dto) {
        final TransactionCopy copy = repository.findById(dto.getId())
                .orElseThrow(() -> new SQLException("Nenhum registro encontrado."));

        final TransactionCopy transaction = repository.save(copy);
        return TransactionCopyAdapter.toDto(transaction);
    }

}
