package com.bibichkov.onlineshop.web;


import com.bibichkov.onlineshop.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllProductsServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> mockProducts = List.of(
                Product.builder().id(1L).name("Guitar").price(100).build(),
                Product.builder().id(2L).name("Phone").price(1110).build(),
                Product.builder().id(3L).name("TV").price(44).build()
        );

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("products", mockProducts);

        String page = pageGenerator.getPage("products.html", parametersMap);
        resp.getWriter().println(page);
    }
}
