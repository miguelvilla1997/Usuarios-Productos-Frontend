/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.carrito;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import py.una.pol.par.entity.Product;
import py.una.pol.par.entity.User;
import py.una.pol.par.model.ProductModel;

/**
 *
 * @author Pc
 */
@WebServlet(name = "CarritoServlet", urlPatterns = {"/CarritoServlet"})
public class CarritoServlet extends HttpServlet {

    private static CarritoImpl carrito;

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
            out.println("<title>Servlet CarritoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CarritoServlet at " + request.getContextPath() + "</h1>");
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
        //Mostrar todos los items
        carrito = (CarritoImpl) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new CarritoImpl();
            request.getSession(false);
            request.getSession().setAttribute("carrito", carrito);
            request.setAttribute("action", "showAll");
        }
        if (request.getParameter("action").equals("showAll")) {
            RequestDispatcher rd = request.getRequestDispatcher("jsp/carrito.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        } else if (request.getParameter("action").equals("add")) {
            request.setAttribute("action", "add");
            this.doPost(request, response);
        } else if (request.getParameter("action").equals("delete")) {
            request.setAttribute("action", "delete");
            this.doDelete(request, response);
            RequestDispatcher rd = request.getRequestDispatcher("jsp/carrito.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }
        if (request.getParameter("action").equals("confirm")) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("about.html");
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
        String idProducto = request.getParameter("id");
        String cantidad = request.getParameter("cantidad");

        //proceso cant x precio..
        ProductModel pm = new ProductModel();
        Product p = pm.getProductById(Integer.valueOf(idProducto));
        CarritoItem item = new CarritoItem(p, Integer.valueOf(cantidad), p.getPrecio());
        String message = "Ok";
        if (!carrito.addItem(item)) {
            message = "No OK";
        }
        request.setAttribute("message", message);
        request.setAttribute("action", "showAll");
        RequestDispatcher rd = request.getRequestDispatcher("jsp/carrito.jsp");
        if (rd != null) {
            rd.forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Para eliminar un item
        String id = request.getParameter("id");
        String message = "Ok";
        if (!carrito.deleteItem(Integer.valueOf(id))) {
            message = "No OK";
        }
        request.setAttribute("message", message);
        request.setAttribute("action", "showAll");

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
