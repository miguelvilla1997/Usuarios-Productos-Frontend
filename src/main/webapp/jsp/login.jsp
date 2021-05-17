<%-- 
    Document   : login
    Created on : 06/05/2021, 08:20:50 PM
    Author     : Pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="/maven-api-client/login" method="POST">
            <input placeholder="Usuario" type="text" name="usuario" value="" />
            <input placeholder="Contrasenha" type="password" name="password" value="" />
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
