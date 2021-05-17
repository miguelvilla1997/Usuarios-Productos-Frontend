<%@page import="py.una.pol.par.entity.User"%>
<%

    User user = (User) request.getSession().getAttribute("user");

%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pagina de Inicio</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <link rel="stylesheet" media="(max-width: 1500px)" href="css/index.css" />
    </head>
    <body>
        <header>
            <nav>
                <button class="nav-button" onclick="mostrar()">Menú</button>
                <a href="/maven-api-client/index.jsp" class="nav-enlace hide">Inicio</a>
                <% if (user != null && user.getClientType() == 0){
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
        <footer>
            <p>©Copyright 2050 de nadie. Todos los derechos revertidos.</p>
        </footer>
        <script src="js/mostrar.js"></script>
    </body>
</html>
