/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.client;

import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import py.una.pol.par.entity.Category;
import py.una.pol.par.entity.Product;

/**
 *
 * @author Mauricio
 */
public class ProductRestClient {

    public ArrayList<Product> getProducts() {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/maven-api-rest/rest/pmcs/products");
        Response response = target.request().get();
        GenericType<ArrayList<Product>> genericType = new GenericType<ArrayList<Product>>() {
        };
        ArrayList<Product> values = response.readEntity(genericType);
        response.close();
        return values;
    }

    public Product getProductById(Integer id) {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/maven-api-rest/rest/pmcs/products/" + id);
        Response response = target.request().get();
        Product values = response.readEntity(Product.class);
        response.close();
        return values;
    }

    public boolean addProduct(Product u) {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/maven-api-rest/rest/pmcs/products");
        Entity<Product> someEntity = Entity.entity(u, MediaType.APPLICATION_JSON);
        Response response = target.request().post(someEntity);
        Product values = response.readEntity(Product.class);
        response.close();
        return true;
    }

    public boolean removeProduct(Integer id) {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/maven-api-rest/rest/pmcs/products/" + id);
        Response response = target.request().delete();
        response.close();
        return true;
    }

    public boolean updateProduct(Product u) {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/maven-api-rest/rest/pmcs/products");
        Entity<Product> someEntity = Entity.entity(u, MediaType.APPLICATION_JSON);
        Response response = target.request().put(someEntity);
        response.close();
        return true;
    }

    public Category getCategoryById(Integer id) {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/maven-api-rest/rest/pmcs/categories/" + id);
        Response response = target.request().get();
        Category values = response.readEntity(Category.class);
        response.close();
        return values;
    }

    public Category getCategoryByProductId(Integer id) {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/maven-api-rest/rest/pmcs/categories/product/" + id);
        Response response = target.request().get();
        Category values = response.readEntity(Category.class);
        response.close();
        return values;
    }

    public ArrayList<Category> getCategories() {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/maven-api-rest/rest/pmcs/categories");
        Response response = target.request().get();
        GenericType<ArrayList<Category>> genericType = new GenericType<ArrayList<Category>>() {
        };
        ArrayList<Category> values = response.readEntity(genericType);
        response.close();
        return values;
    }
}
