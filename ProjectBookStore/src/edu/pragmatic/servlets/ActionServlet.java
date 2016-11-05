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


@WebServlet("/ActionServlet")
public class ActionServlet implements Servlet{

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
		BookStore bookStore = new BookStore();
		
		String bookId =request.getParameter("hiddenBookId");
		String action =request.getParameter("hiddenBookAction");
		String titleBook =request.getParameter("hiddenBookTitle");
		
		
		action = (action == null) ? "Add" : action;

		
		if( action.equals("editBook")){		

			try {				
				String clause = "WHERE book_id="+bookId;	
				Map<String, BookEntries> map = bookStore.bookList(clause);		
				
				httpRequest.getSession().setAttribute("bookList",  bookStore.bookList(clause));
				httpRequest.getSession().setAttribute("getKeyMap",  map.get(titleBook));
				httpRequest.getSession().setAttribute("titleBook", titleBook);
				httpRequest.getSession().setAttribute("action",  "Update");
				
				httpRequest.getSession().setAttribute("checked", "" );
				if(map.get(titleBook).IsForeignLiterature()){
				   httpRequest.getSession().setAttribute("checked", "checked" );
				}
				
				httpResponse.sendRedirect(nameOfProject+"/views/addBook.jsp");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equals("deleteBook")){		
			
			try {
				String book_id = request.getParameter("hiddenBookId");
				bookStore.deleteBookRecord(book_id);				
				httpRequest.getSession().setAttribute("showMessage",  "show");
				httpRequest.getSession().setAttribute("messageText",  "You are deleted the book successfully");
				httpRequest.getSession().setAttribute("bookList",  bookStore.bookList(""));
			} catch (NumberFormatException e) {				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
			httpResponse.sendRedirect(nameOfProject+"/views/bookList.jsp");
			
		}else if( action.equals("Update")){
			
			bookId = request.getParameter("bookId");		
			String[] requestParameters = {
					httpRequest.getParameter("catalog_number"),
					httpRequest.getParameter("title"),
					httpRequest.getParameter("author"),
					httpRequest.getParameter("price"),
					httpRequest.getParameter("name_of_publishing"),
					httpRequest.getParameter("foreign_literature"),
					httpRequest.getParameter("numbers_of_book")
			};
			
			
			try {
				bookStore.updateBookRecord(bookId,requestParameters);
				httpRequest.getSession().setAttribute("showMessage",  "show");
				httpRequest.getSession().setAttribute("messageText",  "You are updated the book details successfully");
				httpRequest.getSession().setAttribute("bookList",  bookStore.bookList(""));
				httpResponse.sendRedirect(nameOfProject+"/views/bookList.jsp");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if( action.equals("Add")){
			//when click add book button	

			httpRequest.getSession().setAttribute("bookList",  "");
			httpRequest.getSession().setAttribute("getKeyMap",  "");
			httpRequest.getSession().setAttribute("titleBook", "");
			httpRequest.getSession().setAttribute("action",  "Insert");

			httpResponse.sendRedirect(nameOfProject+"/views/addBook.jsp");
		}else if( action.equals("Insert")){
			//when insert book
			String[] requestParameters = {
					httpRequest.getParameter("catalog_number"),
					httpRequest.getParameter("title"),
					httpRequest.getParameter("author"),
					httpRequest.getParameter("price"),
					httpRequest.getParameter("name_of_publishing"),
					httpRequest.getParameter("foreign_literature"),
					httpRequest.getParameter("numbers_of_book")
			};

		    if(httpRequest.getParameter("author") != null){		    
				  try {
					int rs = bookStore.insertBookRecord(requestParameters);
					httpRequest.getSession().setAttribute("showMessage",  "show");
					httpRequest.getSession().setAttribute("messageText",  "You are added the book successfully");
					httpRequest.getSession().setAttribute("bookList",  bookStore.bookList(""));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		    
		    httpResponse.sendRedirect(nameOfProject+"/views/bookList.jsp");
		    
		}else if(action.equals("sale")){
			try {
				int saleBookResult = bookStore.saleBook(bookId);
				httpRequest.getSession().setAttribute("showMessage",  "show");
				httpRequest.getSession().setAttribute("messageText",  "You are sold the book successfully");
				httpRequest.getSession().setAttribute("bookList",  bookStore.bookList(""));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			  httpResponse.sendRedirect(nameOfProject+"/views/bookList.jsp");
			
		}
		
		
		
		//httpRequest.getSession().setAttribute("bookStore", bookStore);	
		
		
	}
	
	

}
