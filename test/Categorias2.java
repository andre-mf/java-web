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

@WebServlet(urlPatterns = {"/Categorias2"})
public class Categorias2 extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String query = "select * from categorias", html="";
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "kg33");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertCategorias</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='Categorias2' method='POST'>");
            out.println("<label>Nome:<input type='text' name='nome'/></label>");
            out.println("<select name='categorias_id'>");
 
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                out.println("<option value='"+rs.getInt("id")+"'>"+rs.getString("nome")+"</option>");
                html +="<tr>";
                html +="<td>";
                html +=rs.getInt("id");
                html +="</td>";
                html +="<td>";
                html +=rs.getString("nome");
                html +="</td>";
                html +="<td>";
                html +=rs.getString("categorias_id");
                html +="</td>";
                html +="<td>";
                html +="<a href=Categorias1?acao=excluir&id="+rs.getInt("id")+">Excluir</a>";
                html +="</td>";
                html +="</tr>";
            }
            out.println("</select>");
            out.println("<button type='submit'>Enviar</button>");
            out.println("</form>");
            out.println("<h1>LISTA DE CATEGORIAS</h1>");
            out.println("<table border>");
            out.println("<tr>");
            out.println("<th>");
            out.println("ID");
            out.println("</th>");
            out.println("<th>");
            out.println("NOME");
            out.println("</th>");
            out.println("<th>");
            out.println("CATEGORIAS");
            out.println("</th>");
            out.println("<th>");
            out.println("EXCLUIR");
            out.println("</th>");
            out.println("</tr>");
            out.println(html);
            
            out.println("</table>");
            
            out.println("</body></html>");
            query = "insert into categorias(nome, categorias_id) values(?,?)";
            
            if( request.getParameter("nome")!= null){
               
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString (1, request.getParameter("nome"));
                preparedStmt.setString (2, request.getParameter("categorias_id"));
                preparedStmt.execute();
                con.close();
            }
            
            // Para excluir
            if(request.getParameter("acao").equals("excluir")){
            
                String queryExcluir = "DELETE FROM categorias WHERE id = ?";
                PreparedStatement preparedStmt = con.prepareStatement(queryExcluir);
                preparedStmt.setString (1, request.getParameter("id"));
                preparedStmt.execute();
                con.close();
            }
            
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Categorias2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Categorias2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Categorias2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Categorias2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}