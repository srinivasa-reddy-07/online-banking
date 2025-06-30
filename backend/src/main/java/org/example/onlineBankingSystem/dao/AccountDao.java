package org.example.onlineBankingSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.example.onlineBankingSystem.config.DBConfig;
import org.example.onlineBankingSystem.model.Account;

public class AccountDao {
  public void createAccount(Account account) throws SQLException {
    String sql = """
            INSERT INTO accounts (account_id, customer_id, account_number, bank_id, account_type,
                                  opening_balance, cached_balance, status, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

    try (Connection conn = DBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setLong(1, account.accountId());
      stmt.setLong(2, account.customerId());
      stmt.setString(3, account.accountNumber());
      stmt.setLong(4, account.bankId());
      stmt.setString(5, account.accountType());
      stmt.setBigDecimal(6, account.openingBalance());
      stmt.setBigDecimal(7, account.cachedBalance());
      stmt.setString(8, account.status());
      stmt.setTimestamp(9, account.createdAt());
      stmt.setTimestamp(10, account.updatedAt());

      stmt.executeUpdate();
      conn.commit();
    }
  }

  public Account getAccountById(Long accountId) throws SQLException {
    String sql = "SELECT * FROM accounts WHERE account_id = ?";
    try (Connection conn = DBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setLong(1, accountId);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return new Account(
            rs.getLong("account_id"),
            rs.getLong("customer_id"),
            rs.getString("account_number"),
            rs.getLong("bank_id"),
            rs.getString("account_type"),
            rs.getBigDecimal("opening_balance"),
            rs.getBigDecimal("cached_balance"),
            rs.getString("status"),
            rs.getTimestamp("created_at"),
            rs.getTimestamp("updated_at"));
      }
    }
    return null;
  }

  public void updateAccountStatus(Long accountId, String newStatus) throws SQLException {
    String sql = "UPDATE accounts SET status = ?, updated_at = ? WHERE account_id = ?";
    try (Connection conn = DBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, newStatus);
      stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
      stmt.setLong(3, accountId);

      stmt.executeUpdate();
      conn.commit();
    }
  }

  public void deleteAccount(Long accountId) throws SQLException {
    String sql = "DELETE FROM accounts WHERE account_id = ?";
    try (Connection conn = DBConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setLong(1, accountId);
      stmt.executeUpdate();
      conn.commit();
    }
  }

  public List<Account> getAllAccounts() throws SQLException {
    List<Account> accounts = new ArrayList<>();
    String sql = "SELECT * FROM accounts";

    try (Connection conn = DBConfig.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        accounts.add(new Account(
            rs.getLong("account_id"),
            rs.getLong("customer_id"),
            rs.getString("account_number"),
            rs.getLong("bank_id"),
            rs.getString("account_type"),
            rs.getBigDecimal("opening_balance"),
            rs.getBigDecimal("cached_balance"),
            rs.getString("status"),
            rs.getTimestamp("created_at"),
            rs.getTimestamp("updated_at")));
      }
    }
    return accounts;
  }

}
