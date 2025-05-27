package com.currencyexchange.config;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void init() {
        File dbFile = new File("finance.db");
        if (!dbFile.exists()) {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:finance.db");
                 Statement stmt = conn.createStatement()) {

                // Выполнение schema.sql
                InputStream schemaInput = DatabaseInitializer.class.getResourceAsStream("/db/schema.sql");
                assert schemaInput != null;
                String schemaSql = new String(schemaInput.readAllBytes(), StandardCharsets.UTF_8);
                stmt.executeUpdate(schemaSql);
                schemaInput.close();

                // Выполнение data.sql (опционально)
                InputStream dataInput = DatabaseInitializer.class.getResourceAsStream("/db/data.sql");
                assert dataInput != null;
                String dataSql = new String(dataInput.readAllBytes(), StandardCharsets.UTF_8);
                stmt.executeUpdate(dataSql);
                dataInput.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
