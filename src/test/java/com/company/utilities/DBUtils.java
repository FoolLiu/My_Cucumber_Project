package com.company.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    protected static Connection connection;
    protected static Statement statement;
    protected static ResultSet resultSet;


    public static void createConnection() throws SQLException {
        dbUrl = ConfigurationReader.getProperty("dbUrl");
        dbUser = ConfigurationReader.getProperty("dbUser");
        dbPassword = ConfigurationReader.getProperty("dbPassword");
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public static ResultSet runSQLQuery(String query) throws Exception {
        createConnection();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public static void destroy() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }


}



