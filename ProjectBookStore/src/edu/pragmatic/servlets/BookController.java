package edu.pragmatic.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pragmatic.bookstore.BookStore;
import edu.pragmatic.interfaces.BookEntries;

@WebServlet("/BookController")
public class BookController implements Servlet{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String nameOfProject = httpRequest.getContextPath();
		
		
		String searchBook = request.getParameter("searchBook") != null ? request.getParameter("searchBook").trim() : "";
		
		response.setContentType("text/plain");
		//response.getWriter().write(greetings);
		
		try {		
		    BookStore bookStore = new BookStore();		    
		    if(searchBook != null){
				String clause = (searchBook != "") ? "WHERE LOWER(title) LIKE LOWER('%"+searchBook+"%') ORDER BY book_id" : "";
				
				httpRequest.getSession().setAttribute("bookStore", bookStore);
				httpRequest.getSession().setAttribute("bookList",  bookStore.bookList(clause));
				response.setContentType("text/plain");
			}
		    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	   httpResponse.sendRedirect(nameOfProject+"/views/bookList.jsp");
	   
		
		
	
		
		

		
			
		
		
	}
	
	

}
