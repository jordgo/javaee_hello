package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import com.google.gson.Gson;

import entity.User;
import services.LoginService;
import validation.Validation;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	  private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

	private static final long serialVersionUID = 1L;
       

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
		
		if (Validation.isNameValid(name) && Validation.isPasswordValid(password)) {
			User user = LoginService.getUser(name, password);
			
			if(user != null) {
				LOGGER.info(""+user);
				String jsonUser = new Gson().toJson(user); 
				HttpSession session = request.getSession();
				session.setAttribute("id", user.getId());
				out.write("Welcome "+jsonUser);	
			} else {
				out.write("User Not Found");
			}
			
		} else {
			out.write("Bad name ="+Validation.isNameValid(name)+" password="+Validation.isPasswordValid(password));
		}
	}

}
