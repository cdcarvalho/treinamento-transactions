package br.com.treinamento.transactions.adapters;

import br.com.treinamento.transactions.domain.TransactionCopy;
import br.com.treinamento.transactions.dto.TransactionDTO;

public class TransactionCopyAdapter {

    public static TransactionDTO toDto(final TransactionCopy transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .build();
    }

    public static TransactionCopy toDomain(final TransactionDTO transaction) {
        return TransactionCopy.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .build();
    }
}
