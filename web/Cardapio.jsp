<%@page import="java.sql.*" contentType="text/html" pageEncoding="UTF-8"%>
    <%! String query = "select a.*, b.nome as categoria from cardapios as a inner join categorias as b on a.categorias_id = b.id order by id"; %>
        <html>

        <head>
            <title>Cardápio</title>

            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />

        </head>

        <body>

            
            
            
            
            <!-- Fixed navbar -->
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                            aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Gastronomia</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="">Categorias</a></li>
                            <li><a href="">Cardápio</a></li>
                            <li><a href="">Logins</a></li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </nav>

            <div class="container" style="margin-top: 60px;">

            <%
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(query);
             %>

                    <h1 class='text-center'>Cardápio</h1>
                    <a class='btn btn-warning' href='FormularioCardapio'><span class='glyphicon glyphicon-edit'></span> Novo</a>
                    <br>
                    <br>
                    <table class='table table-bordered table-hover table-striped table-condensed'>
                        <thead>
                            <tr>
                                <th width='20%'>Ações</th>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Categoria</th>
                                <th>Qtd</th>
                                <th>Valor Venda</th>
                            </tr>
                        </thead>
                        <%
                        while(rs.next()){
                        %>
                            <tr>
                            <td>
                
                                
                            <a class='btn btn-primary' href=FormularioCardapio?id="+rs.getInt("id")+"><span class='glyphicon glyphicon-pencil'></span> Alterar</a> 
                            <a class='btn btn-danger' href=ProcessamentoCardapio?id="+rs.getInt("id")+"&acao=excluir><span class='glyphicon glyphicon-remove'></span> Excluir</a>
                
                            </td>
                
                            <td>
                            <%out.println(rs.getInt("id"));%>
                            </td>
                            <td>
                            <%out.println(rs.getString("nome"));%>
                            </td>
                            <td>
                            <%out.println(rs.getString("categoria"));%>
                            </td>
                            <td>
                            <%out.println(rs.getString("qtde"));%>
                            </td>
                            <td>
                            <%out.println(rs.getString("valor_venda"));%>
                            </td>
                            </tr>
                    </table>
            <%
                }
                }catch(SQLException e){
                out.println("Falha no banco de dados :"+e.getMessage());
                }
            %>
            </div>
        </body>

        </html>