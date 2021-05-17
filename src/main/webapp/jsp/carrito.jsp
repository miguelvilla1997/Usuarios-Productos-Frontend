<%-- 
    Document   : carrito
    Created on : 27/04/2021, 10:40:24 PM
    Author     : Pc
--%>

<%@page import="py.una.pol.par.entity.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="py.una.pol.par.carrito.CarritoItem"%>
<%@page import="py.una.pol.par.carrito.CarritoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    CarritoImpl carrito = (CarritoImpl) request.getSession().getAttribute("carrito");
    Iterator<CarritoItem> itProduct = carrito.getItems().iterator();
    User user = (User) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <title>Carrito</title>
        <link rel="stylesheet" media="(max-width: 1500px)" href="css/product.css" />
        <link rel="stylesheet" media="(max-width: 1500px)" href="css/index.css" />
    </head>
    <body>
        <header>
            <nav>
                <button class="nav-button" onclick="mostrar()">Menú</button>
                <a href="/maven-api-client/index.jsp" class="nav-enlace hide">Inicio</a>
                <% if (user != null && user.getClientType() == 0) {
                %>
                <a href="/maven-api-client/UserServlet" class="nav-enlace hide">Users</a>
                <% } %>
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
            <h1>Mis productos</h1>   

            <table >
                <thead>
                <th>Cantidad</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Total</th>
                <th>Acciones</th>
                </thead>
                <tbody>
                    <%while (itProduct.hasNext()) {
                            CarritoItem item = itProduct.next();%>
                    <tr>
                        <td data-label="Cantidad"><%= item.getCantidad()%></td>
                        <td data-label="Nombre"><%= item.getProducto().getName()%></td>
                        <td data-label="Precio"><%= item.getProducto().getPrecio()%></td>
                        <td data-label="Total"><%= item.getSubTotal()%></td>
                        <td data-label="Acciones">
                            <form action="/maven-api-client/CarritoServlet" method ="get">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="id" value="<%=item.getProducto().getId()%>"/>
                                <input type="submit" value="Borrar"/>
                            </form></td>
                    </tr>
                    <%}%>                
                </tbody>           
            </table>
            <form action="/maven-api-client/CarritoServlet">
                <input type="hidden" name="action" value="confirm"/>
                <input type="submit" value="Confirmar" />
            </form>
        </div>

        <footer>
            <p>©Copyright 2050 de nadie. Todos los derechos revertidos.</p>
        </footer>

        <script src="js/mostrar.js"></script>
    </body>
</html>
