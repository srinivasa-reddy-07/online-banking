package org.example.onlineBankingSystem.model;

import java.sql.Timestamp;

public record Ticket(
    Long ticketId,
    Long customerId,
    String ticketType,
    String description,
    String status,
    Timestamp createdAt,
    Timestamp updatedAt
) {}