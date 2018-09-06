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
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Logins"})
public class Logins extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        
         if(!session.getAttribute("codigo").equals("123")){
            response.sendRedirect("/LoginServlets");
        }
        
        try (PrintWriter out = response.getWriter()) {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
            
            String query = "select * from logins";
            
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
            out.println("<a class='btn btn-warning' href='FormularioLogins'><span class='glyphicon glyphicon-edit'></span> Novo</a>");
            out.println("<br>");
            out.println("<br>");
            out.println("<table class='table table-bordered table-hover table-striped table-condensed'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th width='20%'>Ações</th>");
            out.println("<th>ID</th>");
            out.println("<th>Usuário</th>");
            out.println("</tr>");
            out.println("</thead>");
            
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
            out.println("<tr>");
            out.println("<td>");
                
            out.println("<a class='btn btn-primary' href=FormularioLogins?id="+rs.getInt("id_login")+"><span class='glyphicon glyphicon-pencil'></span> Alterar</a> ");
            out.println("<a class='btn btn-danger' href=ProcessamentoLogins?id_login="+rs.getInt("id_login")+"&acao=excluir><span class='glyphicon glyphicon-remove'></span> Excluir</a>");
                
            out.println("</td>");
                
            out.println("<td>");
            out.println(rs.getInt("id_login"));
            out.println("</td>");
            out.println("<td>");
            out.println(rs.getString("usuario"));
            out.println("</td>");
            out.println("</tr>");
            }

            
            out.println("</table>");
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Logins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Logins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
