<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        int i;
        for (i=1; i <= 1000; i++){
            out.println("<p style='font-size: " + i + "px;'>" + i + "</p>");
        }
        %>
    </body>
</html>
