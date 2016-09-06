package com.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class InsertData {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/test";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	static {

		try {

			insertDataIntoUserTable();

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	private static void insertDataIntoUserTable() throws Exception {

		Connection dbConnection = null;
		Statement statement = null;

		try {

			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			
			Random generator = new Random();

			for (int i = 1; i <= 100; i++) {

				String insertTableSQL = "INSERT INTO PRODUCTS (ID, STOREID) VALUES (':id',':storeid')";

				insertTableSQL = insertTableSQL.replaceAll(":id", String.valueOf(i));
				insertTableSQL = insertTableSQL.replaceAll(":storeid", "S1");
				System.out.println(insertTableSQL);

				statement.addBatch(insertTableSQL);

			}

			statement.executeBatch();

			System.out.println("Record is inserted into USER table!");

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

}