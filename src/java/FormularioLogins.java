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

@WebServlet(urlPatterns = {"/FormularioLogins"})
public class FormularioLogins extends HttpServlet {

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
            out.println("<title>Logins</title>");            
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
            out.println("<h1 class='text-center'>Logins</h1>");
            out.println("<br>");
            
            // Monta o formulário para INSERT ou UPDATE
            if (request.getParameter("id") == null){
            
            // INSERT
            out.println("<form class=\"form-horizontal\" method=\"post\" action=\"ProcessamentoLogins?acao=salvar\">");
            out.println("<input type=\"hidden\" name=\"id_login\" value=\"\">");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"usuario\" class=\"col-sm-2 control-label\">Usuário</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"usuario\" name=\"usuario\" value=\"\">");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"senha\" class=\"col-sm-2 control-label\">Senha</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"password\" class=\"form-control\" id=\"senha\" name=\"senha\" value=\"\">");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<div class=\"col-sm-offset-2 col-sm-10\">");
            out.println("<button type=\"submit\" class=\"btn btn-success\"><span class='glyphicon glyphicon-pencil'></span> Salvar</button>");
            out.println("<a class=\"btn btn-danger\" href=\"Logins\"><span class='glyphicon glyphicon-arrow-left'></span> Voltar</a>");
            out.println("</div>");
            out.println("</div>");
                
            } else {
                
            // UPDATE
                
            out.println("<form class=\"form-horizontal\" method=\"post\" action=\"ProcessamentoLogins?acao=salvar\">");
            int id = Integer.parseInt(request.getParameter("id"));
            out.println("<input type=\"hidden\" name=\"id_login\" value=\""+id+"\">");
            
            String query = "SELECT * FROM logins WHERE id_login = " + id + "";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            ResultSet rs = preparedStmt.executeQuery(query);
            
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"usuario\" class=\"col-sm-2 control-label\">Usuário</label>");
            out.println("<div class=\"col-sm-10\">");
            while(rs.next()){
            out.println("<input type='text' class='form-control' id='usuario' name='usuario' value='"+ rs.getString("usuario") + "'>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"senha\" class=\"col-sm-2 control-label\">Senha</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type='password' class='form-control' id='senha' name='senha' value='"+ rs.getString("senha") + "'>");
            }
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<div class=\"col-sm-offset-2 col-sm-10\">");
            out.println("<button type=\"submit\" class=\"btn btn-success\"><span class='glyphicon glyphicon-pencil'></span> Salvar</button>");
            out.println("<a class=\"btn btn-danger\" href=\"Logins\"><span class='glyphicon glyphicon-arrow-left'></span> Voltar</a>");
            out.println("</div>");
            out.println("</div>");
                
            }
                        
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
            Logger.getLogger(FormularioLogins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormularioLogins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormularioLogins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormularioLogins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
