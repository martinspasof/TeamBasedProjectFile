package edu.pragmatic.bookstore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import edu.pragmatic.enums.EnumBooks;
import edu.pragmatic.interfaces.BookEntries;

import edu.pragmatic.mysqlDb.MysqlDatabase;



public class BookStore{
	//public Map<String, Book> books;
	public Map<String, BookEntries> bookImpl;
	
	MysqlDatabase mysqlDatabase = new MysqlDatabase();
	BookEntries bookEntries = new Book();	
	
	public BookStore() {
	//	books = new HashMap<String, Book>();
		bookImpl = new HashMap<String, BookEntries>();
	}
	
		
	public BookEntries searchBook(String bookTitle) {
		return bookImpl.get(bookTitle);
	}

	
	public Map<String, BookEntries> bookList(String clause) throws ClassNotFoundException, SQLException {		
	      String query = "SELECT * FROM book "+clause;
	      
          Connection conn = mysqlDatabase.connectionToDb();
	      // create the java statement
	      Statement st = conn.createStatement();	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);

	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	String title = rs.getString("title");	        
		    bookEntries.addTitle(title);	
		        
	        
	        
	        if (!bookImpl.containsKey(title)) {
	        	BookEntries bookEntries = new Book();
	        	int bookId = rs.getInt("book_id");
	        	bookEntries.setBookId(bookId);
		        String catalogNumber = rs.getString("catalog_number");
		        bookEntries.setCatalogNumber(catalogNumber);
		              
		        String author = rs.getString("author");
		        bookEntries.addAuthor(author);
		       
		        Double price = rs.getDouble("price");
		        bookEntries.setPrice(price);
		        String nameOfPublishing = rs.getString("name_of_publishing");	
		        bookEntries.addPublishing(nameOfPublishing);
		        boolean isForeignLiterature = rs.getBoolean("foreign_literature");	        
		        bookEntries.setIsForeignLiterature(isForeignLiterature);       
		        
		        int numbersOfBook = rs.getInt("numbers_of_book");
		        bookEntries.setNumbersOfBook(numbersOfBook);
	        	
	        	 bookImpl.put(title, bookEntries);
	            
	        }
	       	        
	      
	        
	      }
	      st.close();
	      
		return this.bookImpl;
	}
	
	public String enumsForeignBook(boolean isForeignLiterature){
		String getIsForeignLiterature = isForeignLiterature ? EnumBooks.FOREIGN.name() : EnumBooks.LOCAL.name();
		
		return getIsForeignLiterature.toLowerCase();
	}
	
	public int insertBookRecord(String[] requests) throws ClassNotFoundException, SQLException{

	  String requestCatalogNumber = requests[0];
	  String requestTitle = requests[1];
	  String requestAuthor = requests[2];
	  String requestPrice = requests[3];
	  String requestPublishing = requests[4];
	  Integer requestForeignLiterature = (requests[5] == null) ? 0 : 1;
	  String requestNumbersOfBook  = requests[6];
		  
      Connection conn = mysqlDatabase.connectionToDb();
      Statement st = conn.createStatement(); 
	  String comma = "'";
	  String query = "INSERT INTO book(catalog_number,title,author,price,name_of_publishing,foreign_literature, numbers_of_book)  "
		 		+ "VALUES("+comma+requestCatalogNumber+comma+","+comma+requestTitle+comma+","+comma+requestAuthor+comma+","+requestPrice+","+comma+requestPublishing+comma+","+comma+requestForeignLiterature+comma+","+comma+requestNumbersOfBook+comma+") ";

      
	      
	      // execute the query, and get a java result	   
		  int rs = st.executeUpdate(query);
	      conn.close();      
	      
	      
	      return rs;
	      
	      
	}
	
	public int deleteBookRecord(String bookId) throws ClassNotFoundException, SQLException{	  
			  
	      Connection conn = mysqlDatabase.connectionToDb();
	      Statement st = conn.createStatement(); 		  
		  String query = "DELETE FROM book WHERE book_id="+bookId;
		      
	      // execute the query, and get a java result	   
		  int rs = st.executeUpdate(query);
	      conn.close(); 
	      
	      return rs;
		      
		      
     }
	
	public int saleBook(String bookId) throws ClassNotFoundException, SQLException{	  
		  
	      Connection conn = mysqlDatabase.connectionToDb();
	      Statement st = conn.createStatement(); 		  
		  String query = "UPDATE book SET numbers_of_book=numbers_of_book-1 WHERE book_id="+bookId;
		  
	      // execute the query, and get a java result	   
		  int rs = st.executeUpdate(query);
	      conn.close(); 
	      
	      return rs;		      
		      
   }

	
	public int updateBookRecord(String bookId,String[] requests) throws ClassNotFoundException, SQLException{	  
		  
		 String requestCatalogNumber = requests[0];
		  String requestTitle = requests[1];
		  String requestAuthor = requests[2];
		  String requestPrice = requests[3];
		  String requestPublishing = requests[4];
		  Integer requestForeignLiterature = (requests[5] == null) ? 0 : 1;
		  String requestNumbersOfBook  = requests[6];
		  
			  
	      Connection conn = mysqlDatabase.connectionToDb();
	      Statement st = conn.createStatement(); 
		  String delimiter = ",";
		  String comma = "'";
		  String query = "UPDATE book"
				  		+ " SET catalog_number="+comma+requestCatalogNumber+comma+delimiter
				  		+ "title="+comma+requestTitle+comma+delimiter
				  		+ "author="+comma+requestAuthor+comma+delimiter
				  		+ "price="+requestPrice+delimiter
				  		+ "name_of_publishing="+comma+requestPublishing+comma+delimiter
				  		+ "foreign_literature="+comma+requestForeignLiterature+comma+delimiter
				  		+ "numbers_of_book="+comma+requestNumbersOfBook+comma
				  		+ "WHERE book_id="+bookId;


	      
		      
		      // execute the query, and get a java result	   
			  int rs = st.executeUpdate(query);
		      conn.close();      
		      
		      
		      return rs;
		      
		      
		}
	
	public BookEntries search(String book) {
		return bookImpl.get(book);
	}
	
	
	public String[] userAccess(String firstName,String lastName)  throws ClassNotFoundException, SQLException {
         Connection conn = mysqlDatabase.connectionToDb();
	      // create the java statement
	      Statement st = conn.createStatement();
	      
		 String comma = "'";
		//user_enum_type_id=1 - the user is admin or user_enum_type_id=0 is seller
	      String query = "SELECT * "
	      		            + "FROM users us "
	      		        + "LEFT JOIN `enum_values` env "
	      		        + "ON env.id=us.user_enum_type_id "
	      		        + "WHERE LOWER(first_name)="+comma+firstName.toLowerCase()+comma
	      		        +" AND LOWER(last_name)="+comma+lastName.toLowerCase()+comma;

	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      String[] result = new String[10];
 
		      while (rs.next()) {
		    	  String resFirstName = rs.getString("first_name");
		    	  result[0] = resFirstName;
		    	  
		    	  
		    	  String resLastName = rs.getString("last_name");
		    	  result[1] = resLastName;
		    	
		    	  
		    	  String resAccess = rs.getString("user_enum_type_id");
		    	  result[2] = resAccess;
		    	  
		    	  
		      }
	    
		      
		      conn.close(); 
	        
	        
	        return result;
	      
	     
	}


}
