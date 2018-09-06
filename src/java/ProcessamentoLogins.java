import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ProcessamentoLogins"})
public class ProcessamentoLogins extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
            
            if (request.getParameter("acao").equals("salvar")){
                
                // Condicional para INSERT ou UPDATE
                if (request.getParameter("id_login").equals("")){
                    String query = "INSERT INTO logins (`usuario`, `senha`) VALUES (?, ?)";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString (1, request.getParameter("usuario"));
                    preparedStmt.setString (2, request.getParameter("senha"));
                    preparedStmt.execute();
                    con.close();
                    response.sendRedirect("Logins");
                } else {
                    String query = "UPDATE logins SET `usuario`= ?, `senha`= ? WHERE `id_login`= ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString (1, request.getParameter("usuario"));
                    preparedStmt.setString (2, request.getParameter("senha"));
                    preparedStmt.setInt (3, Integer.parseInt(request.getParameter("id_login")));
                    preparedStmt.execute();
                    con.close();
                    response.sendRedirect("Logins");
                }
                
                } else {
                // DELETE
                    String query = "DELETE FROM Logins WHERE id_login = ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setInt (1, Integer.parseInt(request.getParameter("id_login")));
                    preparedStmt.execute();
                    con.close();
                    response.sendRedirect("Logins");
                
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessamentoLogins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessamentoLogins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessamentoLogins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessamentoLogins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
