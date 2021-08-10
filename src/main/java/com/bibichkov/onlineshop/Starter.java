package com.bibichkov.onlineshop;

import com.bibichkov.onlineshop.dao.jdbc.ConnectionFactory;
import com.bibichkov.onlineshop.dao.jdbc.JdbcProductDao;
import com.bibichkov.onlineshop.service.ProductService;
import com.bibichkov.onlineshop.util.CachedPropertiesReader;
import com.bibichkov.onlineshop.web.GetAllProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Properties;

public class Starter {

    public static void main(String[] args) throws Exception {

        // properties
        CachedPropertiesReader cachedPropertiesReader = new CachedPropertiesReader("database.properties");
        Properties properties = cachedPropertiesReader.getCachedProperties();

        // dao
        ConnectionFactory connectionFactory = new ConnectionFactory(properties);
        JdbcProductDao jdbcProductDao = new JdbcProductDao(connectionFactory);

        // service
        ProductService productService = new ProductService();

        // servlet
        GetAllProductsServlet getAllProductsServlet = new GetAllProductsServlet();

        //config web server

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new GetAllProductsServlet()), "/");
        context.addServlet(new ServletHolder(new AddNewProductServlet()), "/add");
        context.addServlet(new ServletHolder(new EditProductServlet()), "/edit");
        context.addServlet(new ServletHolder(new RemoveProductServlet()), "/remove");


        Server server = new Server(8080);
        server.setHandler(context);

        server.addConnector(connection);
        server.start();
    }
}
