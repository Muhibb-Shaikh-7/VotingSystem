import java.sql.*;
public class dbConnection {


	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=null;
		String dbname="student";
		String Username="root";
		String Password="fahad5534";
		con=DriverManager.getConnection("jdbc:/mysql://localhost/3306/"+dbname,Username, Password);
	    System.out.println("Connection EStablished")
	}
	public static  void createTable(Connection con) throws SQLException {
		String query="create table irfan(id int,password varchar(50))";
		Statement st=con.createStatement();
		st.executeUpdate(query);
		System.out.print(" table created succesfully" );
		
	}
	public static  void insertTable(Connection con) throws SQLException {
		
		String query="";
		
		Statement st=con.createStatement();
		st.executeUpdate(query);
		System.out.print(" data inserted succesfully" );
		
	}
	
	

}
