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
import py.una.pol.par.entity.Category;
import py.una.pol.par.entity.Product;
import py.una.pol.par.model.ProductModel;

/**
 *
 * @author Pc
 */
public class ProductServlet extends HttpServlet {

    private final ProductModel pm = new ProductModel();

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
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
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

        String uri = request.getRequestURI();
        String id = request.getParameter("id");

        if ("".equals(id) || id == null) {
            if (request.getParameter("action") != null && request.getParameter("action").equals("add")) {
                ArrayList<Category> categories = pm.getCategories();
                request.setAttribute("categories", categories);
                RequestDispatcher rd = request.getRequestDispatcher("jsp/addProduct.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            } else {
                //Mostrar todos los productos
                ArrayList<Product> products = pm.getProducts();
                request.setAttribute("products", products);
                request.setAttribute("action", "showAll");
                RequestDispatcher rd = request.getRequestDispatcher("jsp/products.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            }

        } else {
            if (request.getParameter("action") != null && request.getParameter("action").equals("delete")) {
                this.doDelete(request, response);

            } else if (request.getParameter("action").equals("update")) {
                Product p = pm.getProductById(Integer.valueOf(id));
                Category c = pm.getCategoryByProductId(Integer.valueOf(id));
                ArrayList<Category> categories = pm.getCategories();
                request.setAttribute("product", p);
                request.setAttribute("category", c);
                request.setAttribute("categories", categories);
                RequestDispatcher rd = request.getRequestDispatcher("jsp/updateProduct.jsp");
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
            String name = request.getParameter("name");
            String categoryId = request.getParameter("id_category");
            String precio = request.getParameter("precio");
            String stock = request.getParameter("stock");
            String status = request.getParameter("status");
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("id_category", categoryId);
            request.setAttribute("precio", precio);
            request.setAttribute("stock", stock);
            request.setAttribute("status", status);
            this.doPut(request, response);
        } else {
            //Para agregar un nuevo producto
            String name = request.getParameter("name");
            String categoryId = request.getParameter("id_category");
            String precio = request.getParameter("precio");
            String stock = request.getParameter("stock");
            Product p = new Product();
            p.setName(name);
            p.setCategory(pm.getCategoryById(Integer.valueOf(categoryId)));
            p.setPrecio(Double.valueOf(precio));
            p.setStock(Integer.valueOf(stock));
            p.setStatus("A");

            String message = "Ok";
            if (!pm.addProduct(p)) {
                message = "No OK";
            }
            request.setAttribute("message", message);
            request.setAttribute("action", "showAll");
            //Mostrar todos los productos
            ArrayList<Product> products = pm.getProducts();
            request.setAttribute("products", products);
            request.setAttribute("action", "showAll");
            RequestDispatcher rd = request.getRequestDispatcher("jsp/products.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        /*RequestDispatcher rd = request.getRequestDispatcher("/paronline/admin/users.jsp");
        if (rd != null) {
            rd.forward(request, response);
        }*/
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
        String categoryId = request.getParameter("id_category");
        String status = request.getParameter("status");
        String precio = request.getParameter("precio");
        String stock = request.getParameter("stock");
        Product p = new Product();
        p.setId(Integer.valueOf(id));
        p.setName(name);
        p.setCategory(pm.getCategoryById(Integer.valueOf(categoryId)));
        p.setStatus(status);
        p.setPrecio(Double.valueOf(precio));
        p.setStock(Integer.valueOf(stock));

        String message = "Ok";
        if (!pm.updateProduct(p)) {
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
        if (!pm.deleteProduct(Integer.valueOf(id))) {
            message = "No OK";
        }
        ArrayList<Product> products = pm.getProducts();
        request.setAttribute("products", products);
        request.setAttribute("action", "showAll");
        RequestDispatcher rd = request.getRequestDispatcher("jsp/products.jsp");
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
