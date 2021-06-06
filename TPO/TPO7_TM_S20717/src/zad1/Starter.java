package zad1;

import Model.Book;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/BookList")
public class Starter extends javax.servlet.http.HttpServlet {

    private DataSource dataSource;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Connection connection = null;
        try {
            synchronized (dataSource) {
                connection = dataSource.getConnection();
            }
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From Books");
            List<Book> bookList = new ArrayList<>();
            while(resultSet.next()){
                bookList.add(new Book(resultSet.getInt("IdBook"),resultSet.getString("BookName"),resultSet.getString("Author"),resultSet.getDouble("Price")));
            }
            resultSet.close();
            statement.close();
            connection.close();
            request.setAttribute("List", bookList);
            request.getRequestDispatcher("BookList.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        try {
            Context context = new InitialContext();
            Context con = (Context) context.lookup("java:comp/env");
            dataSource = (DataSource) con.lookup("jdbc/sqlserver");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
