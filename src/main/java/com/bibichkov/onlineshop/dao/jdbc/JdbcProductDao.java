package com.bibichkov.onlineshop.dao.jdbc;


import com.bibichkov.onlineshop.dao.ProductDao;
import com.bibichkov.onlineshop.entity.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String ADD_SQL = "insert into products(name, price) values (?, ?)";
    private static final String GET_ALL_SQL = "select id, name, price from products";
    private static final String GET_BY_ID_SQL = "select id, name, price from products where id = ?";
    private static final String DELETE_SQL = "delete from products where id = ?";
    private static final String UPDATE_SQL = "update products set name = ?, price = ? where id = ?";


    private final DataSource dataSource;

    public JdbcProductDao(DataSource connectionFactory) {
        this.dataSource = connectionFactory;
    }

    @Override
    public Iterable<Product> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement getAllStatement = connection.prepareStatement(GET_ALL_SQL)) {
            getAllStatement.execute();
            try (ResultSet resultSet = getAllStatement.getResultSet()){
                List<Product> products = new ArrayList<>();
                while (resultSet.next()){
                    Product product = extractRow(resultSet);
                    products.add(product);
                }
                return products;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get products from db", e);
        }
    }

    @Override
    public void add(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement addStatement = connection.prepareStatement(ADD_SQL)) {
            addStatement.setString(1, product.getName());
            addStatement.setDouble(2, product.getPrice());
            addStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to add product to db", e);
        }
    }

    @Override
    public Product getById(Long productId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement getStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            getStatement.setLong(1, productId);
            getStatement.execute();
            try(ResultSet resultSet = getStatement.getResultSet()) {
                if(!resultSet.next()){
                    return null;
                }
                return extractRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get product with id", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_SQL)) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete product from db", e);
        }

    }

    @Override
    public void update(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_SQL)) {
            updateStatement.setString(1, product.getName());
            updateStatement.setDouble(2, product.getPrice());
            updateStatement.setLong(3, product.getId());
            updateStatement.executeUpdate();

            if (updateStatement.getUpdateCount() == 0){
                throw new RuntimeException("No product was updated!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to update product in db", e);
        }
    }

    private Product extractRow(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getLong(1))
                .name(resultSet.getString(2))
                .price(resultSet.getDouble(3))
                .build();
    }
}
