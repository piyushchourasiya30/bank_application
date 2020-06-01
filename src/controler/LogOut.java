package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOut
 */
public class LogOut extends HttpServlet {
	private HttpSession session;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	session= request.getSession();
	response.sendRedirect("/BANKAPP/index.html");
	session.invalidate();
	
}
}
