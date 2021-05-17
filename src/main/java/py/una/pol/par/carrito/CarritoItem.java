/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.carrito;

import py.una.pol.par.entity.Product;

/**
 *
 * @author Pc
 */
public class CarritoItem {
    private Integer cantidad;
    
    private Double subTotal;
    
    private Product producto;

    public CarritoItem(Product p, Integer cantidad, Double precio) {
      this.subTotal = this.calcularSubTotal(precio, cantidad);
      this.producto = p;
    }

    public CarritoItem() {
        
    }
    
    public Double calcularSubTotal(Double precio, Integer cantidad){
        this.cantidad = cantidad;
        this.subTotal= precio * cantidad;
        return subTotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Product getProducto() {
        return producto;
    }

    public void setProducto(Product producto) {
        this.producto = producto;
    }
    
}
