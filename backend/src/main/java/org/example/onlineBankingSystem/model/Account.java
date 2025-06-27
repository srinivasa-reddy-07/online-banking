package org.example.onlineBankingSystem.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record Account(
    Long accountId,
    Long customerId,
    String accountNumber,
    Long bankId,
    String accountType,
    BigDecimal openingBalance,
    BigDecimal cachedBalance,
    String status,
    Timestamp createdAt,
    Timestamp updatedAt
) {}