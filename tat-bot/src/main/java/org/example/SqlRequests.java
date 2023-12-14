package org.example;

import java.sql.*;


public class SqlRequests {
    private String input_message_;
    private String output_message_;
    public void find() throws SQLException {
        Connection connection_ = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tatdic", "postgres", "2209");
        Statement statement = connection_.createStatement();
        statement.execute("SELECT * FROM tatrus");
        ResultSet result = statement.getResultSet();
        while (result.next()) {
            System.out.println(result.getString("tat") + " " + result.getString("rus"));
        }
        connection_.close();
    }
}
