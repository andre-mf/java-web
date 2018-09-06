import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Categorias"})
public class Categorias extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
         
        HttpSession session = request.getSession(true);
        
         if(!session.getAttribute("codigo").equals("123")){
            response.sendRedirect("index.html");
        }
        
        try (PrintWriter out = response.getWriter()) {
            
            String query = "select * from categorias", html="";
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\'>");
            out.println("<title>Categorias</title>");            
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
            html += "<div class='container' style='margin-top: 60px;'>";
            html += "<h1 class='text-center'>Categorias</h1>";
            html += "<a class='btn btn-warning' href='FormularioCategorias'><span class='glyphicon glyphicon-edit'></span> Novo</a>";
            html += "<br>";
            html += "<br>";
            html += "<table class='table table-bordered table-hover table-striped table-condensed'>";
            html += "<thead>";
            html += "<tr>";
            html += "<th width='20%'>Ações</th>";
            html += "<th>ID</th>";
            html += "<th>Nome</th>";
            html += "</tr>";
            html += "</thead>";
            
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                html +="<tr>";
                html +="<td>";
                
                html += "<a class='btn btn-primary' href=FormularioCategorias?id="+rs.getInt("id")+"><span class='glyphicon glyphicon-pencil'></span> Alterar</a> ";
                html += "<a class='btn btn-danger' href=ProcessamentoCategorias?id="+rs.getInt("id")+"&acao=excluir><span class='glyphicon glyphicon-remove'></span> Excluir</a>";
                
                html +="</td>";
                
                html +="<td>";
                html +=rs.getInt("id");
                html +="</td>";
                html +="<td>";
                html +=rs.getString("nome");
                html +="</td>";
                html +="</tr>";
            }

            
            html += "</table>";
            html += "</div>";
            html += "</body>";
            html += "</html>";
            out.println(html);
            
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}