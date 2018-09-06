import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/FormularioCardapio"})
public class FormularioCardapio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\'>");
            out.println("<title>Cardápio</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            // Navbar
            out.println("<nav class='navbar navbar-inverse navbar-fixed-top'>");
            out.println("<div class='container'>");
            out.println("<div class='navbar-header'>");
            out.println("<button type='button' class='navbar-toggle collapsed' data-toggle='collapse' data-target='#navbar' aria-expanded='false' aria-controls='navbar'>");
            out.println("<span class='sr-only'>Toggle navigation</span>");
            out.println("<span class='icon-bar'></span>");
            out.println("<span class='icon-bar'></span>");
            out.println("<span class='icon-bar'></span>");
            out.println("</button>");
            out.println("<a class='navbar-brand' href='#'>Gastronomia</a>");
            out.println("</div>");
            out.println("<div id='navbar' class='navbar-collapse collapse'>");
            out.println("<ul class='nav navbar-nav'>");
            out.println("<li><a href='Categorias'>Categorias</a></li>");
            out.println("<li><a href='Cardapio'>Cardápio</a></li>");
            out.println("<li><a href='Logins'>Logins</a></li>");
            out.println("</ul>");
            out.println("</div><!--/.nav-collapse -->");
            out.println("</div>");
            out.println("</nav>");
            // Fim da navbar
            
            // Container
            out.println("<div class='container' style='margin-top: 60px;'>");
            out.println("<h1 class='text-center'>Cardápio</h1>");
            out.println("<br>");
            
            // Monta o formulário para INSERT ou UPDATE
            if (request.getParameter("id") == null){
            
            // INSERT
            out.println("<form class=\"form-horizontal\" method=\"post\" action=\"ProcessamentoCardapio?acao=salvar\">");
            out.println("<input type=\"hidden\" name=\"id\" value=\"\">");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"nome\" class=\"col-sm-2 control-label\">Nome</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"nome\" name=\"nome\" value=\"\">");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"descricao\" class=\"col-sm-2 control-label\">Descrição</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"descricao\" name=\"descricao\" value=\"\">");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"categorias_id\" class=\"col-sm-2 control-label\">Categoria</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<select name=\"categorias_id\" id=\"categorias_id\" class=\"form-control\">");
            out.println("<option value=\"\">Selecione</option>");
            
            // Recupera as categorias cadastradas no banco
            String query = "SELECT * FROM categorias";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                out.println("<option value='"+rs.getInt("id")+"'>"+rs.getString("nome")+"</option>");
            }
            
            out.println("</select>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"qtd\" class=\"col-sm-2 control-label\">Quantidade</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"qtde\" name=\"qtde\" value=\"\">");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"valor_venda\" class=\"col-sm-2 control-label\">Valor Venda</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"valor_venda\" name=\"valor_venda\" value=\"\">");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"valor_compra\" class=\"col-sm-2 control-label\">Valor Compra</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"valor_compra\" name=\"valor_compra\" value=\"\">");
            out.println("</div>");
            out.println("</div>");
                
            } else {
                
            // UPDATE
                
            out.println("<form class=\"form-horizontal\" method=\"post\" action=\"ProcessamentoCardapio?acao=salvar\">");
            int id = Integer.parseInt(request.getParameter("id"));
            out.println("<input type=\"hidden\" name=\"id\" value=\""+id+"\">");
            
            String query = "SELECT * FROM cardapios WHERE id = " + id + "";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            ResultSet rs = preparedStmt.executeQuery(query);
            
            while(rs.next()){
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"nome\" class=\"col-sm-2 control-label\">Nome</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"nome\" name=\"nome\" value='"+ rs.getString("nome") + "'>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"descricao\" class=\"col-sm-2 control-label\">Descrição</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"descricao\" name=\"descricao\" value='"+ rs.getString("descricao") + "'>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"qtde\" class=\"col-sm-2 control-label\">Quantidade</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"qtde\" name=\"qtde\" value='"+ rs.getString("qtde") + "'>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"valor_venda\" class=\"col-sm-2 control-label\">Valor Venda</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"valor_venda\" name=\"valor_venda\" value='"+ rs.getString("valor_venda") + "'>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"valor_compra\" class=\"col-sm-2 control-label\">Valor Compra</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"valor_compra\" name=\"valor_compra\" value='"+ rs.getString("valor_compra") + "'>");
            out.println("</div>");
            out.println("</div>");
            }
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"categorias_id\" class=\"col-sm-2 control-label\">Categoria</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<select name=\"categorias_id\" id=\"categorias_id\" class=\"form-control\">");
            out.println("<option value=\"\">Selecione</option>");
            
            // Recupera as categorias cadastradas no banco
            query = "SELECT * FROM categorias";
            PreparedStatement stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                out.println("<option value='"+rs.getInt("id")+"'>"+rs.getString("nome")+"</option>");
            }
            
            out.println("</select>");
            out.println("</div>");
            out.println("</div>");
                
            }
            
            out.println("<div class=\"form-group\">");
            out.println("<div class=\"col-sm-offset-2 col-sm-10\">");
            out.println("<button type=\"submit\" class=\"btn btn-success\"><span class='glyphicon glyphicon-pencil'></span> Salvar</button>");
            out.println("<a class=\"btn btn-danger\" href=\"Cardapio\"><span class='glyphicon glyphicon-arrow-left'></span> Voltar</a>");
            out.println("</div>");
            out.println("</div>");
                        
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");


        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormularioCardapio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormularioCardapio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormularioCardapio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormularioCardapio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
