package org.example.onlineBankingSystem.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record Transaction(
    Long transactionId,
    String transactionType,
    Long fromAccountId,
    Long toAccountId,
    BigDecimal amount,
    String remarks,
    String status,
    Timestamp createdAt
) {}