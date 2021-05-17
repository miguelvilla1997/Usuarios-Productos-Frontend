/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import py.una.pol.par.entity.User;
import py.una.pol.par.model.UserModel;

/**
 *
 * @author Pc
 */
public class LoginServlet extends HttpServlet {
    
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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        //me llega la url "/login?out"
        String action = (request.getParameter("action") != null ? request.getParameter("action") : "");
        HttpSession sesion = request.getSession();
        if (action.equals("out")) {
            sesion.invalidate();
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        } else {
             RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
            if (rd != null) {
                rd.forward(request, response);
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
        HttpSession sesion = request.getSession();
        String usu, pass;
        usu = request.getParameter("usuario");
        pass = request.getParameter("password");
        User userLogin = um.getUserByLoginName(usu);
        //deberíamos buscar el usuario en la base de datos, pero dado que se escapa de este tema, ponemos un ejemplo en el mismo código
        if (usu.equals(userLogin.getLoginName()) && pass.equals(userLogin.getPasswd()) && sesion.getAttribute("user") == null) {
            //si coincide usuario y password y además no hay sesión iniciada
            sesion.setAttribute("user", userLogin);
            //redirijo a página con información de login exitoso
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
        } else {
            //lógica para login inválido
            RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
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
