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

public class Category extends BaseEntity<Integer> {

    public Category() {
        super(0,"");
    }

    public Category(Integer id, String name) {
        super(id, name);
    }

}
