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

@WebServlet(urlPatterns = {"/ProcessamentoCardapio"})
public class ProcessamentoCardapio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
            
            if (request.getParameter("acao").equals("salvar")){
                
                // Condicional para INSERT ou UPDATE
                if (request.getParameter("id").equals("")){
                    
                    out.println(request.getParameter("nome"));
                    out.println(request.getParameter("descricao"));
                    out.println(request.getParameter("qtde"));
                    out.println(request.getParameter("valor_venda"));
                    out.println(request.getParameter("valor_compra"));
                    out.println(request.getParameter("categorias_id"));
                    
                    
                    String query = "INSERT INTO cardapios (`nome`, `descricao`, `qtde`, `valor_venda`, `valor_compra`, `categorias_id`) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString (1, request.getParameter("nome"));
                    preparedStmt.setString (2, request.getParameter("descricao"));
                    preparedStmt.setInt (3, Integer.parseInt(request.getParameter("qtde")));
                    preparedStmt.setString (4, request.getParameter("valor_venda"));
                    preparedStmt.setString (5, request.getParameter("valor_compra"));
                    preparedStmt.setInt (6, Integer.parseInt(request.getParameter("categorias_id")));
                    preparedStmt.execute();
                    con.close();
                    response.sendRedirect("Cardapio");
                
                } else {
                    
                    out.println("UPDATE");
                                        out.println(request.getParameter("nome"));
                    out.println(request.getParameter("descricao"));
                    out.println(request.getParameter("qtde"));
                    out.println(request.getParameter("valor_venda"));
                    out.println(request.getParameter("valor_compra"));
                    out.println(request.getParameter("categorias_id"));
                    
                    
                    
                    String query = "UPDATE cardapios SET `nome`= ?, `descricao`= ?, `qtde`= ?, `valor_venda`= ?, `valor_compra`= ?, `categorias_id`= ? WHERE `id`= ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString (1, request.getParameter("nome"));
                    preparedStmt.setString (2, request.getParameter("descricao"));
                    preparedStmt.setInt (3, Integer.parseInt(request.getParameter("qtde")));
                    preparedStmt.setString (4, request.getParameter("valor_venda"));
                    preparedStmt.setString (5, request.getParameter("valor_compra"));
                    preparedStmt.setInt (6, Integer.parseInt(request.getParameter("categorias_id")));
                    preparedStmt.setInt (7, Integer.parseInt(request.getParameter("id")));
                    preparedStmt.execute();
                    con.close();
                    response.sendRedirect("Cardapio");
                }
                
                } else {
                // DELETE
                    String query = "DELETE FROM cardapios WHERE id = ?";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setInt (1, Integer.parseInt(request.getParameter("id")));
                    preparedStmt.execute();
                    con.close();
                    response.sendRedirect("Cardapio");
                
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessamentoCardapio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessamentoCardapio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessamentoCardapio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessamentoCardapio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
