package br.com.itau.transactions.repository;

import br.com.itau.transactions.domain.TransactionCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface TransactionCopyRepository extends JpaRepository<TransactionCopy, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    Optional<TransactionCopy> findById(Long Long);
}
