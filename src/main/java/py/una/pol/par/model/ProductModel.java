/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.model;

import java.util.ArrayList;
import py.una.pol.par.client.ProductRestClient;
import py.una.pol.par.entity.Category;
import py.una.pol.par.entity.Product;

/**
 *
 * @author Pc
 */
public class ProductModel {

    private final ProductRestClient productClient;

    public ProductModel() {
        productClient = new ProductRestClient();
    }

    public ArrayList<Product> getProducts() {
        return productClient.getProducts();
    }

    public Product getProductById(Integer id) {
        return productClient.getProductById(id);
    }

    public boolean addProduct(Product u) {
        return productClient.addProduct(u);
    }

    public boolean updateProduct(Product u) {
        return productClient.updateProduct(u);
    }

    public boolean deleteProduct(Integer id) {
        return productClient.removeProduct(id);
    }

    public Category getCategoryById(Integer id) {
        return productClient.getCategoryById(id);
    }

    public Category getCategoryByProductId(Integer id) {
        return productClient.getCategoryByProductId(id);
    }

    public ArrayList<Category> getCategories() {
        return productClient.getCategories();
    }
}
