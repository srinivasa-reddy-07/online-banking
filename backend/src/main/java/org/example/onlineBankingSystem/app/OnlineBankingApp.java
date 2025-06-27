package org.example.onlineBankingSystem.app;

import org.example.onlineBankingSystem.config.DBConfig;

import java.sql.Connection;

public class OnlineBankingApp {
    public static void main(String[] args) {
        try {
            Connection connection = DBConfig.getConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
