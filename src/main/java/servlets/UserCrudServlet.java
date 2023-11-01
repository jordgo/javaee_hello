package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.User;
import services.UserService;

/**
 * Servlet implementation class UserCrud
 */
@WebServlet("/UserCrud")
public class UserCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	UserService userService;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = userService.getAll();
		String jsonUsers = new Gson().toJson(users);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(jsonUsers);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		try {
			User user = gson.fromJson(request.getReader(), User.class);
			userService.createUser(user);
			out.write("{OK}");
		} catch (Exception e) {
			e.getStackTrace();
			out.write("{message: "+e.getMessage()+"}");
			
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		try {
			User user = gson.fromJson(request.getReader(), User.class);
			long userId = user.getId();
			if(userId != 0L) {
				User newUser = userService.updateUser(user);
				String jsonUser = gson.toJson(newUser);
				out.write(jsonUser);
			} else {
				out.write("{\"message\": \"User Not Found userId=" +userId+ "\"}");
			}
		} catch (Exception e) {
			e.getStackTrace();
			out.write("{\"message\": "+e.getMessage()+"}");
			
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		try {
			User user = gson.fromJson(request.getReader(), User.class);
			long userId = user.getId();
			if(userId != 0L) {
				userService.deleteUser(user);
				out.write("OK");
			} else {
				out.write("{\"message\": \"User Not Found userId=" +userId+ "\"}");
			}
		} catch (Exception e) {
			e.getStackTrace();
			out.write("{\"message\": "+e.getMessage()+"}");
			
		}
	}

}
