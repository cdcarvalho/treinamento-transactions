package br.com.itau.transactions.adapters;

import br.com.itau.transactions.domain.Transaction;
import br.com.itau.transactions.dto.TransactionDTO;

public class TransactionAdapter {

    public static TransactionDTO toDto(final Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .build();
    }

    public static Transaction toDomain(final TransactionDTO transaction) {
        return Transaction.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .build();
    }
}
