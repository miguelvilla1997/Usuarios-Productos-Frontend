/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.model;

import java.util.ArrayList;
import py.una.pol.par.client.UserRestClient;
import py.una.pol.par.entity.User;

/**
 *
 * @author mauricio
 */
public class UserModel {

    private final UserRestClient userClient;

    public UserModel() {
        userClient = new UserRestClient();
    }

    public ArrayList<User> getUsers() {
        return userClient.getUsers();
    }

    public User getUserById(Integer id) {
        return userClient.getUserById(id);
    }

    public User getUserByLoginName(String loginName) {
        return userClient.getUserByLoginName(loginName);
    }

    public boolean addUser(User u) {
        return userClient.addUser(u);
    }
    
     public boolean updateUser(User u) {
        return userClient.updateUser(u);
    }

    public boolean deleteUser(Integer id) {
        return userClient.removeUser(id);
    }
}
