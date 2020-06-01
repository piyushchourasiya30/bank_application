package controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model = new Model();
		String npwd = request.getParameter("password");
		String opwd = request.getParameter("oldpassword");
		session = request.getSession();
		model.setAccno((int) session.getAttribute("accno"));
		model.setPassword(opwd);
		try {
			boolean value = model.changePassword(npwd);
			if (value == true) {
				session.invalidate();

				response.sendRedirect("/BankApplication/sucsesspasw.html");

			} else {
				response.sendRedirect("/BankApplication/failpasschange.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


