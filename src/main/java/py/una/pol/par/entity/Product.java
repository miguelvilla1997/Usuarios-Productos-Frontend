/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.entity;

import py.una.pol.par.commons.entity.BaseEntity;

/**
 *
 * @author Pc
 */

public class Product extends BaseEntity<Integer> {
    
    private Category category;

    private String status;
    
    private Double precio;

    private Integer stock;

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    

    public Product(){
        
    }
    public Product(Integer id, String name){
        super(id,name);
    }
    public Product(Category category, Integer id, String name, Double precio, Integer stock) {
        super(id, name);
        this.category = category;
        this.precio = precio;
        this.stock = stock;
        this.status = "A";
    }
    
    public Product(Category category, Integer id, String name,Double precio, Integer stock, String status) {
        super(id, name);
        this.category = category;
        this.precio = precio;
        this.stock = stock;
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" + "category=" + category + ", status=" + status + '}';
    }

}
