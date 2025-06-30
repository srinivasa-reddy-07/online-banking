package org.example.onlineBankingSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.onlineBankingSystem.config.DBConfig;
import org.example.onlineBankingSystem.model.Transaction;

public class TransactionDao {

  public void createTransaction(Transaction transaction) throws SQLException {
    String sql = """
            INSERT INTO transactions (transaction_id, transaction_type, from_account_id, to_account_id,
                                      amount, remarks, status, created_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

    try (Connection conn = DBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setLong(1, transaction.transactionId());
      stmt.setString(2, transaction.transactionType());
      stmt.setLong(3, transaction.fromAccountId());
      stmt.setLong(4, transaction.toAccountId());
      stmt.setBigDecimal(5, transaction.amount());
      stmt.setString(6, transaction.remarks());
      stmt.setString(7, transaction.status());
      stmt.setTimestamp(8, transaction.createdAt());

      stmt.executeUpdate();
      conn.commit();
    }
  }

  public Transaction getTransactionById(Long id) throws SQLException {
    String sql = "SELECT * FROM transactions WHERE transaction_id = ?";

    try (Connection conn = DBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return new Transaction(
            rs.getLong("transaction_id"),
            rs.getString("transaction_type"),
            rs.getLong("from_account_id"),
            rs.getLong("to_account_id"),
            rs.getBigDecimal("amount"),
            rs.getString("remarks"),
            rs.getString("status"),
            rs.getTimestamp("created_at"));
      }
    }
    return null;
  }

  public void updateTransactionStatus(Long txnId, String newStatus) throws SQLException {
    String sql = "UPDATE transactions SET status = ? WHERE transaction_id = ?";

    try (Connection conn = DBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, newStatus);
      stmt.setLong(2, txnId);

      stmt.executeUpdate();
      conn.commit();
    }
  }

  public void deleteTransaction(Long txnId) throws SQLException {
    String sql = "DELETE FROM transactions WHERE transaction_id = ?";

    try (Connection conn = DBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setLong(1, txnId);
      stmt.executeUpdate();
      conn.commit();
    }
  }

  public List<Transaction> getAllTransactions() throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    String sql = "SELECT * FROM transactions";

    try (Connection conn = DBConfig.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        Transaction txn = new Transaction(
            rs.getLong("transaction_id"),
            rs.getString("transaction_type"),
            rs.getLong("from_account_id"),
            rs.getLong("to_account_id"),
            rs.getBigDecimal("amount"),
            rs.getString("remarks"),
            rs.getString("status"),
            rs.getTimestamp("created_at"));
        transactions.add(txn);
      }
    }
    return transactions;
  }
}
