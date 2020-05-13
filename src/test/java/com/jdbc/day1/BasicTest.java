package com.jdbc.day1;

import java.sql.*;

public class BasicTest {
    public static void main(String[] args) throws SQLException {

        String URL = "jdbc:oracle:thin:@18.209.55.1:1521:xe";
        String username = "hr";
        String password = "hr";

        Connection connection = DriverManager.getConnection(URL, username, password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        while (resultSet.next()) {
            System.out.printf("%-4s %-10s %-10s ", resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
//            System.out.print(resultSet.getString(1) + " " + resultSet.getString(2));
            System.out.println();
        }

        resultSet.beforeFirst(); // to come back to the beginning of result set

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("JDBC name: " + databaseMetaData.getDriverName());
        System.out.println("JDBC version: " + databaseMetaData.getDriverVersion());
        System.out.println("Database name: " + databaseMetaData.getDatabaseProductName());
        System.out.println("Database version: " + databaseMetaData.getDatabaseProductVersion());

        System.out.println("Number of column: " + resultSetMetaData.getColumnCount());
        System.out.println("Label of first column: " + resultSetMetaData.getColumnName(1));
        System.out.println("Datatype of first column: " + resultSetMetaData.getColumnTypeName(1));


        for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount() ; columnIndex++) {
            System.out.printf("%-20s", resultSetMetaData.getColumnName(columnIndex));
        }
        System.out.println();
        while (resultSet.next()) {
            for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount() ; columnIndex++) {
                System.out.printf("%-20s", resultSet.getString(columnIndex));
            }
            System.out.println();
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
