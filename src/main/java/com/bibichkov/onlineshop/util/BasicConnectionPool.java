package com.bibichkov.onlineshop.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {

    public BasicConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    public static BasicConnectionPool create(
        String url, String user,
        String password) {

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                pool.add(createConnection(url, user, password));
            } catch (SQLException e) {
                System.out.println("Error connecting to database!");
                e.printStackTrace();
            }
        }
        return new BasicConnectionPool(url, user, password, pool);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
            return DriverManager.getConnection(url, user, password);
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public String getUrl() throws IOException {
        return Utils.getDbParamsFromProps("datasource.driver") +
                Utils.getDbParamsFromProps("datasource.path");
    }

    @Override
    public String getUser() throws IOException {
        return Utils.getDbParamsFromProps("datasource.username");
    }

    @Override
    public String getPassword() throws IOException {
        return Utils.getDbParamsFromProps("datasource.password");
    }


    private static final int INITIAL_POOL_SIZE = 10;
    private final String url;
    private final String user;
    private final String password;
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();
}
