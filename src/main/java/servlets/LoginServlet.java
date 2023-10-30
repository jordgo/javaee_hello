package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public LoginServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
		
		if (Validation.isNameValid(name) && Validation.isPasswordValid(password)) {
			String user = LoginService.getUser(name, password);
			System.out.println("1111111111111111111 "+user);
			out.write("Welcome");
		} else {
			out.write("Bad name ="+Validation.isNameValid(name)+" password="+Validation.isPasswordValid(password));
		}
	}

}
