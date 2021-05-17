/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import py.una.pol.par.entity.User;
import py.una.pol.par.model.UserModel;

/**
 *
 * @author mauricio
 */
public class UserServlet extends HttpServlet {

    private final UserModel um = new UserModel();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action");
        String strUserId = request.getParameter("id");
       
        if ("".equals(strUserId) || strUserId == null) {
            if (action != null && action.equals("add")) {
                RequestDispatcher rd = request.getRequestDispatcher("jsp/addUser.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            } else {
                //Mostrar todos los productos
                ArrayList<User> users = um.getUsers();
                request.setAttribute("users", users);
                request.setAttribute("action", "showAll");
                RequestDispatcher rd = request.getRequestDispatcher("jsp/users.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            }
        } else {
            if (action != null && action.equals("delete")) {
                this.doDelete(request, response);

            } else if (action.equals("update")) {
                //Para editar la informacion de un usuario.
                User u = um.getUserById(Integer.valueOf(strUserId));
                request.setAttribute("user", u);
                RequestDispatcher rd = request.getRequestDispatcher("jsp/updateUser.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            }

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action").equals("update")) {
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String loginName = request.getParameter("loginName");
            String passwd = request.getParameter("passwd");
            int clientType = Integer.valueOf(request.getParameter("clientType"));
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("lastname", lastname);
            request.setAttribute("email", email);
            request.setAttribute("loginName", loginName);
            request.setAttribute("passwd", passwd);
            request.setAttribute("status", status);
             request.setAttribute("clientType", clientType);
            this.doPut(request, response);
        } else {
            //Para agregar un nuevo usuario
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String loginName = request.getParameter("loginName");
            String passwd = request.getParameter("passwd");
            int clientType = Integer.valueOf(request.getParameter("clientType"));

            User u = new User();
            u.setName(name);
            u.setLastname(lastname);
            u.setEmail(email);
            u.setLoginName(loginName);
            u.setPasswd(passwd);
            u.setClientType(clientType);

            String message = "Ok";
            if (!um.addUser(u)) {
                message = "No OK";
            }
            request.setAttribute("message", message);
            request.setAttribute("action", "showAll");
            ArrayList<User> users = um.getUsers();
            request.setAttribute("users", users);
            RequestDispatcher rd = request.getRequestDispatcher("jsp/users.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

    }

    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Para actualizar informacion de un usuario
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String loginName = request.getParameter("loginName");
        String passwd = request.getParameter("passwd");
        String clientType = request.getParameter("clientType");
        String status = request.getParameter("status");
        User u = new User();
        u.setId(Integer.valueOf(id));
        u.setName(name);
        u.setLastname(lastName);
        u.setEmail(email);
        u.setLoginName(loginName);
        u.setPasswd(passwd);
        u.setStatus(status);
        u.setClientType(Integer.valueOf(clientType));

        String message = "Ok";
        if (!um.updateUser(u)) {
            message = "No OK";
        }
        request.setAttribute("message", message);
        request.setAttribute("action", "showAll");

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        if (rd != null) {
            rd.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Para eliminar un usuario
        String id = request.getParameter("id");
        String message = "Ok";
        if (!um.deleteUser(Integer.valueOf(id))) {
            message = "No OK";
        }
        ArrayList<User> users = um.getUsers();
        request.setAttribute("users", users);
        request.setAttribute("action", "showAll");
        RequestDispatcher rd = request.getRequestDispatcher("jsp/users.jsp");
        if (rd != null) {
            rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
