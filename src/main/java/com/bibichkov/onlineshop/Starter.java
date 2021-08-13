package com.bibichkov.onlineshop;

import com.bibichkov.onlineshop.dao.jdbc.ConnectionFactory;
import com.bibichkov.onlineshop.dao.jdbc.JdbcProductDao;
import com.bibichkov.onlineshop.service.ProductService;
import com.bibichkov.onlineshop.util.CachedPropertiesReader;
import com.bibichkov.onlineshop.web.AddNewProductServlet;
import com.bibichkov.onlineshop.web.EditProductServlet;
import com.bibichkov.onlineshop.web.GetAllProductsServlet;
import com.bibichkov.onlineshop.web.RemoveProductServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Properties;

public class Starter {

    public static void main(String[] args) throws Exception {

        // properties
        CachedPropertiesReader cachedPropertiesReader =
                new CachedPropertiesReader("src/main/resources/database.properties");
        Properties properties = cachedPropertiesReader.getCachedProperties();

        // dao
        ConnectionFactory connectionFactory = new ConnectionFactory(properties);
        JdbcProductDao jdbcProductDao = new JdbcProductDao(connectionFactory);

        // service
        ProductService productService = new ProductService();

        // servlet
        GetAllProductsServlet getAllProductsServlet = new GetAllProductsServlet();
        AddNewProductServlet addProductsServlet = new AddNewProductServlet();
        EditProductServlet editProductServlet = new EditProductServlet();
        RemoveProductServlet removeProductsServlet = new RemoveProductServlet();

        //config web server

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(getAllProductsServlet), "/");
        context.addServlet(new ServletHolder(addProductsServlet), "/add");
        context.addServlet(new ServletHolder(editProductServlet), "/edit");
        context.addServlet(new ServletHolder(removeProductsServlet), "/remove");


        Server server = new Server(8282);
        server.setHandler(context);

        server.start();
    }
}
