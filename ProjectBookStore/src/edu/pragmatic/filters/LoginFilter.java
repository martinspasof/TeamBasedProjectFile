package edu.pragmatic.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Login
 */
@WebFilter("/LoginServlet")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//sessions
		String firstName = (String) httpRequest.getSession().getAttribute("firstName");
		String lastName = (String) httpRequest.getSession().getAttribute("lastName");
		String password = (String) httpRequest.getSession().getAttribute("pass");
		
		//post values
		String requestFirstName = httpRequest.getParameter("fname");
		String requestLastName = httpRequest.getParameter("lname");
		
		boolean checkValues = ( (firstName == null) &&  (lastName == null) );
		
		
		if (checkValues && (requestFirstName==null || requestLastName==null) ) {
			httpResponse.sendRedirect(httpRequest.getContextPath() +"/views/loginForm.jsp");
		} else if ((requestFirstName != null) &&  (requestLastName != null)) {
			httpRequest.getSession().setAttribute("firstName", requestFirstName);	
			httpRequest.getSession().setAttribute("lastName", requestLastName);
			httpRequest.getSession().setAttribute("password", password);
		}

		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
