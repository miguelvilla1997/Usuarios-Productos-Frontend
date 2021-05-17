<%-- 
    Document   : updateProduct
    Created on : 23/04/2021, 02:50:19 PM
    Author     : Pc
--%>

<%@page import="java.util.Iterator"%>
<%@page import="py.una.pol.par.entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Product p = (Product) request.getAttribute("product");
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
    Iterator<Category> itCategory = categories.iterator();
    Category selected = (Category) request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Product </title>
    </head>
    <body>
        <h1>Update Product</h1>
        <form action="/maven-api-client/ProductServlet" method ="post">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="<%=p.getId()%>"/>
            <input placeholder="Nombre del producto" type="text" name="name" value="<%=p.getName()%>" required/>
            <select name="id_category" required>
                <option >Seleccione una Categoria...</option>
                <% while (itCategory.hasNext()) {
                        Category c = itCategory.next();
                %>
                <option <%= (c.getId() == selected.getId() ? "selected='selected'" : "")%>
                    value="<%= c.getId()%>"><%= c.getName()%></option>
                <%}%>  
            </select>
            <input placeholder="Estado" readonly type="text" name="status" value="<%=p.getStatus()%>" required/>
            <input placeholder="Precio" type="number" name="precio" value="<%=p.getPrecio()%>" required/>
            <input placeholder="Stock" type="number" name="stock" value="<%=p.getStock()%>" required/>
            <input type="submit" value="Actualizar"/>
        </form>
    </body>
    <script>

    </script>
</html>
