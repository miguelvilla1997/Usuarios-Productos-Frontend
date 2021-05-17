<%-- 
    Document   : updateProduct
    Created on : 23/04/2021, 02:50:19 PM
    Author     : Pc
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User p = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form User </title>
    </head>
    <body>
        <h1>Add User</h1>
        <form action="/maven-api-client/UserServlet" method ="post">
            <input type="hidden" name="action" value="add"/>
            <input placeholder="Nombre del usuario" type="text" name="name" value="" required/>
            <input placeholder="Apellido" type="text" name="lastname" value="" required/>
            <input placeholder="Usuario" type="text" name="loginName" value="" required/>
            <input placeholder="Contrasenha" type="password" name="passwd" value=""/>
            <input placeholder="Email" type="email" name="email" value="" required/>
            <input min="0" max="1" placeholder="Tipo" type="number" name="clientType" value="" required/>
            <input type="submit" value="Guardar"/>
        </form>
    </body>
    <script>

    </script>
</html>
