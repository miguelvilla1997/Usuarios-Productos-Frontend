<%-- 
    Document   : products
    Created on : 22/04/2021, 01:15:44 AM
    Author     : Pc
--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<User> lista = (ArrayList<User>) request.getAttribute("users");
    Iterator<User> itProduct = lista.iterator();
    User user = (User) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <title>Users</title>
        <link rel="stylesheet" media="(max-width: 1500px)" href="css/product.css" />
        <link rel="stylesheet" media="(max-width: 1500px)" href="css/index.css" />
    </head>
    <body>

        <header>
            <nav>
                <button class="nav-button" onclick="mostrar()">Menú</button>
                <a href="/maven-api-client/index.jsp" class="nav-enlace hide">Inicio</a>
                <a href="/maven-api-client/UserServlet" class="nav-enlace hide">Users</a>
                <a href="/maven-api-client/ProductServlet" class="nav-enlace hide">Products</a>
                <a href="/maven-api-client/CarritoServlet?action=showAll" class="nav-enlace hide">Carrito</a>
                <div class="nav-enlace hide dropdown">
                    <button class="dropbtn">Logger</button>
                    <div class="dropdown-content">
                        <% if (user == null) {
                        %>
                        <a href="/maven-api-client/login" class="nav-enlace hide">Login</a>
                        <% } %>
                        <% if (user != null) {
                        %>
                        <a href="/maven-api-client/login?action=out" class="nav-enlace hide">Logout</a>
                        <% } %>
                    </div>
                </div>
            </nav>
        </header>
        <div class="container">
            <h1>Usuarios</h1>   
            <% if (user != null && user.getClientType() == 0) {
            %>
            <form action="/maven-api-client/UserServlet" method ="get" >
                <input type="hidden" name="action" value="add"/>
                <input class="add" type="submit" value="Agregar"/>
            </form>
            <% } %>
            <input type="text" id="buscar" onkeyup="busqueda()" placeholder="Buscar en tabla"
                   title="Empieza a escribir para buscar">
            <table >
                <thead>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Usuario</th>
                <th>Tipo Usuario</th>
                <th>Acciones</th>
                </thead>
                <% if (user != null && user.getClientType() == 0) {
                %>
                <tbody>
                    <%while (itProduct.hasNext()) {
                            User u = itProduct.next();%>
                    <tr>
                        <td data-label="Nombre"><%= u.getName()%></td>
                        <td data-label="Apellido"><%= u.getLastname()%></td>
                        <td data-label="Usuario"><%= u.getLoginName()%></td>
                        <td data-label="Tipo Usuario"><%= u.getClientType()%></td>
                        <td data-label="Acciones">
                            <form action="/maven-api-client/UserServlet" method ="get">
                                <input type="hidden" name="action" value="update"/>
                                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                <input id="submit"type="submit" value="Editar"/>
                            </form>
                            <br>
                            <form action="/maven-api-client/UserServlet" method ="get">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                <input type="submit" value="Borrar"/>
                            </form></td>
                    </tr>
                    <%}%>                
                </tbody> 
                <% } %>
            </table>
        </div>

        <footer>
            <p>©Copyright 2050 de nadie. Todos los derechos revertidos.</p>
        </footer>

        <script src="js/mostrar.js"></script>
        <script src="js/busqueda.js"></script>
    </body>
</html>
