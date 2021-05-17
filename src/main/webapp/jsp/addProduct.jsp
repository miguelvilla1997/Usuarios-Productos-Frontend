<%-- 
    Document   : addProduct
    Created on : 23/04/2021, 03:00:06 PM
    Author     : Pc
--%>

<%@page import="java.util.Iterator"%>
<%@page import="py.una.pol.par.entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
    Iterator<Category> itCategory = categories.iterator();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Product</title>
    </head>
    <body>

        <h1>Add Product</h1>
        <form action="/maven-api-client/ProductServlet" method ="post">
            <input type="hidden" name="action" value="add"/>
            <input placeholder="Nombre del producto" type="text" name="name" value="" required/>
            <select name="id_category" required>
                <option >Seleccione una Categoria...</option>
                <% while (itCategory.hasNext()) {
                        Category c = itCategory.next();
                %>
                <option 
                    value="<%= c.getId()%>"><%= c.getName()%></option>
                <%}%>  
            </select>
            <input placeholder="Precio" type="number" name="precio" value="" required/>
            <input placeholder="Cantidad en Stock" type="number" name="stock" value="" required/>
            <input type="submit" value="Guardar"/>
        </form>
    </body>
    <script>

    </script>
</html>

