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
 * Servlet implementation class CheckBalance
 */
public class CheckBalance extends HttpServlet {
	
	private HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model = new Model();
		session = request.getSession();
		model.setAccno((int) session.getAttribute("accno"));
		try {
			boolean value = model.checkBalance();

			if (value == true) {
				session.setAttribute("balance", model.getBalance());
				response.sendRedirect("/BankApplication/DispBalance.jsp");
			} else {
				response.sendRedirect("/BankApplication/loginfail.html");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
// TODO Auto-generated method stub
	}


