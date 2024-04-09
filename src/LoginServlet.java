import java.io.IOException;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        String dbName = "student";
        String dbUserName = "root";
        String dbPassword = "fahad5534";

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, dbUserName, dbPassword);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        if (con == null) {
            System.out.println("Connection not established");
        } else {
            System.out.println("Connection established");
        }

        boolean isValidUser = false;
		try {
			
			isValidUser = validateUser(con, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (isValidUser) {
        	request.setAttribute("userName", userName); // Pass userName as attribute
            RequestDispatcher dispatcher = request.getRequestDispatcher("userProfile.jsp");
            dispatcher.forward(request, response);
        
        } else {
      
            response.sendRedirect("errorPage.html");
        }
    }
    public static boolean validateUser(Connection con ,String userName,String password) throws SQLException {
    	String query="Select * from userInfo where userName=? AND password=?";
    	PreparedStatement st=con.prepareStatement(query);
    	st.setString(1, userName);
    	st.setString(2, password);
    ResultSet rs=	st.executeQuery();
    return rs.next();
    	
    }

}
