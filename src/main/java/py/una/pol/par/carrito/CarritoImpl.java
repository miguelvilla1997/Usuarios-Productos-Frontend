/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.carrito;

import java.util.ArrayList;
import java.util.Collection;
import py.una.pol.par.entity.Product;
import py.una.pol.par.model.ProductModel;

/**
 *
 * @author Pc
 */
public class CarritoImpl {

    private final ArrayList<CarritoItem> items = new ArrayList<>();
    private final ProductModel pm = new ProductModel();

    public ArrayList<CarritoItem> getItems() {
        return items;
    }

    public boolean addItem(CarritoItem item) {
        for (int i = 0; i < items.size(); i++) {
            CarritoItem object = items.get(i);
            if (object.getProducto().getId() == item.getProducto().getId()) {
                object.getProducto().setStock(object.getProducto().getStock() - item.getCantidad());
                pm.updateProduct(object.getProducto());
                item.setCantidad(item.getCantidad() + object.getCantidad());
                items.remove(object);
                items.add(item);
                return true;
            }

        }
        if (items.add(item)) {
            Product p = item.getProducto();
            p.setStock(p.getStock() - item.getCantidad());
            pm.updateProduct(p);
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteItem(Integer id) {
        Product p = pm.getProductById(id);
        //eliminar un item por medio de un producto.
        int tam = items.size();
        for (int i = 0; i < tam; i++) {
            if (p.getId().equals(items.get(i).getProducto().getId())) {
                p.setStock(p.getStock() + items.get(i).getCantidad());
                pm.updateProduct(p);
                items.remove(i);
                return true;
            }
        }

        return false;
    }

}
