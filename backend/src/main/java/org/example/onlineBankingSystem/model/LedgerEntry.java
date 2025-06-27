import java.math.BigDecimal;
import java.sql.Timestamp;

public record LedgerEntry(
    Long entryId,
    Long transactionId,
    Long accountId,
    String entryType,
    BigDecimal amount,
    String remarks,
    Timestamp createdAt
) {}