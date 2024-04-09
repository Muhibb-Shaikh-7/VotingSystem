

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String userName1=request.getParameter("userName");
	      String password1=request.getParameter("password");
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con=null;
		String dbName="student";
		String userName="root";
		String password="fahad5534";
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,userName,password);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		if(con==null) {
			System.out.print("connection not established");
		}
		else {
			
			System.out.print("connection  established");
		}
		try {
		
			insertTable(con,userName1,password1);
			response.sendRedirect("login.html");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static  void createTable(Connection con) throws SQLException {
		String query="create table userInfo(userName varchar(50),password varchar(50))";
		Statement st=con.createStatement();
		st.executeUpdate(query);
		System.out.print(" table created succesfully" );
		
	}
	
public static  void insertTable(Connection con,String userName,String password) throws SQLException {
		
	 String query = "INSERT INTO userInfo(userName, password) VALUES (?, ?)";
	    PreparedStatement preparedStatement = con.prepareStatement(query);
	    preparedStatement.setString(1, userName);
	    preparedStatement.setString(2, password);
	    preparedStatement.executeUpdate();
	    System.out.println("Data inserted successfully");
		
	}


}
