<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="savecontato.jsp" method="POST">
            <h3>Nome</h3>
            <input type="text" name="nome">
            <h3>Zap</h3>
            <input type="text" name="zap">
            <h3>E-mail</h3>
            <input type="text" name="email">
            <br>
            <input type="submit" value="true" name="Enviar">
        </form>
    </body>
</html>