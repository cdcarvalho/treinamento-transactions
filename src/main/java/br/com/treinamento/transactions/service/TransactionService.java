package br.com.treinamento.transactions.service;

import br.com.treinamento.transactions.adapters.TransactionAdapter;
import br.com.treinamento.transactions.domain.Transaction;
import br.com.treinamento.transactions.dto.TransactionDTO;
import br.com.treinamento.transactions.exception.SQLException;
import br.com.treinamento.transactions.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private TransactionCopyService copyService;

    @Transactional
    public TransactionDTO create(final TransactionDTO dto) {
        final Transaction transaction = repository.save(TransactionAdapter.toDomain(dto));
        return TransactionAdapter.toDto(transaction);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionDTO createRequiresNew(final TransactionDTO dto) {
        final Transaction transaction = repository.save(TransactionAdapter.toDomain(dto));
        copyService.createNew(dto);
        return TransactionAdapter.toDto(transaction);
    }

    @Transactional(noRollbackFor = SQLException.class)
    public TransactionDTO noRollback(final TransactionDTO dto) {
        repository.save(TransactionAdapter.toDomain(dto));
        throw new SQLException("No rollback");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public TransactionDTO get(final Long id) {
        final Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum registro encontrado."));
        return TransactionAdapter.toDto(transaction);
    }

    public TransactionDTO createPrivateMethod(final TransactionDTO dto) {
        return privateMethod(dto);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private TransactionDTO privateMethod(final TransactionDTO dto) {
        final Transaction transaction = repository.save(TransactionAdapter.toDomain(dto));
        return TransactionAdapter.toDto(transaction);
    }

    public void createParallel(final TransactionDTO dto) {

        final List<Integer> requestsTotal = this.getRequestsTotal(100);

        final List<TransactionDTO> getTegistros = requestsTotal.stream()
                .parallel()
                .map(indice -> {
                    log.info("Indice: {}", indice);
                    return copyService.getParallel(dto);
                })
                .collect(Collectors.toList());


        final List<TransactionDTO> registros = requestsTotal.stream()
                .parallel()
                .map(indice -> {
                    log.info("Indice: {}", indice);
                    return copyService.createParallel(dto);
                })
                .collect(Collectors.toList());

        log.info("Total de registros inseridos: {}", registros.size());

    }

    private List<Integer> getRequestsTotal(final Integer quantity) {
        return IntStream.range(1, quantity + 1)
                .boxed()
                .collect(Collectors.toList());
    }
}
