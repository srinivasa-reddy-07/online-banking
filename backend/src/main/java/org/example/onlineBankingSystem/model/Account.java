import java.math.BigDecimal;
import java.sql.Timestamp;

public record Account(
    Long accountId,
    Long customerId,
    String accountNumber,
    Integer bankId,
    String accountType,
    BigDecimal openingBalance,
    BigDecimal cachedBalance,
    String status,
    Timestamp createdAt,
    Timestamp updatedAt
) {}