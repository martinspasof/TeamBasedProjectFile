package edu.pragmatic.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pragmatic.bookstore.BookStore;

@WebServlet("/LoginServlet")
public class LoginServlet implements Servlet{

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
		String requestFirstName = httpRequest.getParameter("fname");
		String requestLastName = httpRequest.getParameter("lname");
        String nameOfProject = httpRequest.getContextPath();
        BookStore bookStore = new BookStore();
        
		 if(requestFirstName != null && requestLastName !=null){	
			    
				try {
					String[] resultDb = bookStore.userAccess(requestFirstName,requestLastName);
					boolean isEmptyResult = (resultDb[0] != null) && (resultDb[1] != null);
					if(isEmptyResult && requestFirstName.toLowerCase().equals(resultDb[0].toLowerCase()) && requestLastName.toLowerCase().equals(resultDb[1].toLowerCase())){	
						
						try {	
						    
							httpRequest.getSession().setAttribute("bookStore", bookStore);							
							httpRequest.getSession().setAttribute("userAccess", resultDb[2]);
							
							String clause = "ORDER BY book_id";
							
							httpRequest.getSession().setAttribute("bookList", bookStore.bookList(clause));
						} catch (ClassNotFoundException e) {						
							e.printStackTrace();
						} catch (SQLException e) {						
							e.printStackTrace();
						}	
					   httpResponse.sendRedirect(nameOfProject+"/views/bookList.jsp");					
					}else{		
						httpResponse.getWriter().println( "Wrong username or Password!!!!" );								
						httpResponse.sendRedirect(nameOfProject+"/views/loginForm.jsp");
					}
					resultDb = bookStore.userAccess(requestFirstName,requestLastName);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
		    }
		

	    	
		   
		
	}

}
