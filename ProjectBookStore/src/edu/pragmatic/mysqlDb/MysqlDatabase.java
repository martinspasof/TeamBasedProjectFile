package edu.pragmatic.mysqlDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDatabase {

	public Connection connectionToDb() throws SQLException, ClassNotFoundException{
		
		/*Class.forName("com.mysql.jdbc.Driver") ;
		 org.gjt.mm.mysql.Driver
		 com.mysql.jdbc.Driver
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBNAME", "usrname", "pswd") ;
		Statement stmt = conn.createStatement() ;
		String query = "select columnname from tablename ;" ;
		ResultSet rs = stmt.executeQuery(query) ;*/
		
		
		
		
		// create our mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost/bookstore";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      return conn;
		
	}
	
	
	
}
