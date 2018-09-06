
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

@WebServlet(urlPatterns = {"/ProcessamentoCategorias"})
public class ProcessamentoCategorias extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
            
            if (request.getParameter("acao").equals("salvar")){
                
                // Condicional para INSERT ou UPDATE
                if (request.getParameter("id").equals("")){
                    
                    if (!request.getParameter("categorias_id").equals("")){
                        String query = "INSERT INTO categorias (`nome`, `categorias_id`) VALUES (?, ?)";
                        PreparedStatement preparedStmt = con.prepareStatement(query);
                        preparedStmt.setString (1, request.getParameter("nome"));
                        preparedStmt.setInt (2, Integer.parseInt(request.getParameter("categorias_id")));
                        preparedStmt.execute();
                    } else {
                        String query = "INSERT INTO categorias (`nome`) VALUES (?)";
                        PreparedStatement preparedStmt = con.prepareStatement(query);
                        preparedStmt.setString (1, request.getParameter("nome"));
                        preparedStmt.execute();
                    }
                    con.close();
                    response.sendRedirect("Categorias");
                    
                } else {
                    
                    if (!request.getParameter("categorias_id").equals("")){
                        String query = "UPDATE categorias SET `nome`= ?, `categorias_id`= ? WHERE `id`= ?";
                        PreparedStatement preparedStmt = con.prepareStatement(query);
                        preparedStmt.setString (1, request.getParameter("nome"));
                        preparedStmt.setInt (2, Integer.parseInt(request.getParameter("categorias_id")));
                        preparedStmt.setInt (3, Integer.parseInt(request.getParameter("id")));
                        preparedStmt.execute();
                    } else {
                        String query = "UPDATE categorias SET `nome`= ? WHERE `id`= ?";
                        PreparedStatement preparedStmt = con.prepareStatement(query);
                        preparedStmt.setString (1, request.getParameter("nome"));
                        preparedStmt.setInt (2, Integer.parseInt(request.getParameter("id")));
                        preparedStmt.execute();
                    }
                    con.close();
                    response.sendRedirect("Categorias");
                }
                
                } else {
                // DELETE
                    String query = "DELETE FROM categorias WHERE id = ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setInt (1, Integer.parseInt(request.getParameter("id")));
                    preparedStmt.execute();
                    con.close();
                    response.sendRedirect("Categorias");
                
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessamentoCategorias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessamentoCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessamentoCategorias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessamentoCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
