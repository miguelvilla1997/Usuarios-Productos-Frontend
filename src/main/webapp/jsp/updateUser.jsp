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
        <h1>Update User</h1>
        <form action="/maven-api-client/UserServlet" method ="post">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="<%=p.getId()%>"/>
             <input type="hidden" name="passwd" value="<%=p.getPasswd()%>"/>
            <input placeholder="Nombre del usuario" type="text" name="name" value="<%=p.getName()%>" required/>
            <input placeholder="Apellido" type="text" name="lastname" value="<%=p.getLastname()%>" required/>
            <input placeholder="Usuario" type="text" name="loginName" value="<%=p.getLoginName()%>" required/>
            <input placeholder="Email" type="email" name="email" value="<%=p.getEmail()%>" required/>
            <input placeholder="Estado" readonly type="text" name="status" value="<%=p.getStatus()%>" required/>
            <input placeholder="Tipo" readonly type="number" name="clientType" value="<%=p.getClientType()%>" required/>
            <input type="submit" value="Actualizar"/>
        </form>
    </body>
    <script>

    </script>
</html>
