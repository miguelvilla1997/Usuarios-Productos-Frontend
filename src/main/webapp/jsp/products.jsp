<%-- 
    Document   : products
    Created on : 22/04/2021, 01:15:44 AM
    Author     : Pc
--%>
<%@page import="py.una.pol.par.entity.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Product> lista = (ArrayList<Product>) request.getAttribute("products");
    Iterator<Product> itProduct = lista.iterator();
    User user = (User) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <title>Products</title>
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
            <h1>Productos</h1>   
            <% if (user != null && user.getClientType() == 0) {
            %>
            <form action="/maven-api-client/ProductServlet" method ="get" >
                <input type="hidden" name="action" value="add"/>
                <input class="add" type="submit" value="Agregar"/>
            </form>
            <% } %>
            <input type="text" id="buscar" onkeyup="busqueda()" placeholder="Buscar en tabla"
                   title="Empieza a escribir para buscar">
            <table >
                <thead>
                <th>Nombre</th>
                <th>Categoria</th>
                <th>Estado</th>
                <th>Precio</th>
                <th>Stock</th>
                <th>Acciones</th>
                </thead>
                <tbody>
                    <%while (itProduct.hasNext()) {
                            Product p = itProduct.next();%>
                    <tr>
                        <td data-label="Nombre"><%= p.getName()%></td>
                        <td data-label="Categoria"><%= p.getCategory().getName()%></td>
                        <td data-label="Estado"><%= p.getStatus()%></td>
                        <td data-label="Precio"><%= p.getPrecio()%></td>
                        <td data-label="Stock"><%= p.getStock()%></td>
                        <td data-label="Acciones">
                            <% if (user != null && user.getClientType() == 0) {
                            %>
                            <form action="/maven-api-client/ProductServlet" method ="get">
                                <input type="hidden" name="action" value="update"/>
                                <input type="hidden" name="id" value="<%=p.getId()%>"/>
                                <input id="submit"type="submit" value="Editar"/>
                            </form>
                            <% } %>
                            <br>
                            <form action="/maven-api-client/CarritoServlet" method ="get">
                                <input type="hidden" name="action" value="add"/>
                                <input type="hidden" name="id" value="<%=p.getId()%>"/>
                                <input max="<%= p.getStock()%>" class="carrito" placeholder="Cantidad a comprar"
                                       type="number" name="cantidad" value="" required/>
                                <input type="submit" value="Agregar Carrito"/>
                            </form>
                            <br>
                             <% if (user != null && user.getClientType() == 0) {
                            %>
                            <form action="/maven-api-client/ProductServlet" method ="get">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="id" value="<%=p.getId()%>"/>
                                <input type="submit" value="Borrar"/>
                            </form>  <% } %></td>
                    </tr>
                    <%}%>                
                </tbody>           
            </table>
        </div>

        <footer>
            <p>©Copyright 2050 de nadie. Todos los derechos revertidos.</p>
        </footer>

        <script src="js/mostrar.js"></script>
        <script src="js/busqueda.js"></script>
    </body>
</html>
