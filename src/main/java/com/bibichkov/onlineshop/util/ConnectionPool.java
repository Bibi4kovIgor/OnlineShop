package com.bibichkov.onlineshop.util;

import java.io.IOException;
import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getUrl() throws IOException;
    String getUser() throws IOException;
    String getPassword() throws IOException;
}
