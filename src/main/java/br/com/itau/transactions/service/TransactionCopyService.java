package br.com.itau.transactions.service;

import br.com.itau.transactions.adapters.TransactionCopyAdapter;
import br.com.itau.transactions.domain.TransactionCopy;
import br.com.itau.transactions.dto.TransactionDTO;
import br.com.itau.transactions.exception.SQLException;
import br.com.itau.transactions.repository.TransactionCopyRepository;
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
