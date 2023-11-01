package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout/*")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
	    
		String requri = ((HttpServletRequest) request)
                .getRequestURI().substring(
                    ((HttpServletRequest) request).getContextPath().length() + 1);
		
		if(requri != null) {
			String userid=requri.substring(requri.lastIndexOf('/') + 1);;
		    System.out.println("2222222222222222 "+userid+" "+requri);
		    if(userid != null) {
		    	HttpSession session = request.getSession();
		    	session.removeAttribute(userid);
		    	out.write("OK");
		    } else {
				out.write("User Not Found");
			}
		}
	}


}
