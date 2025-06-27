package org.example.onlineBankingSystem.model;

import java.sql.Timestamp;

public record Customer(
    Long customerId,
    String fullName,
    String email,
    String passwordHash,
    String phone,
    String address,
    String status,
    Timestamp createdAt,
    Timestamp updatedAt
) {}
