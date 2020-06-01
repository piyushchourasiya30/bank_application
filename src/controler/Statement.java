package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

/**
 * Servlet implementation class Statement
 */
public class Statement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model = new Model();
		try {
			session =request.getSession();
			int accno = (int)session.getAttribute("accno");
			model.setAccno(accno);
			ArrayList credit =	model.getCredit();
			ArrayList debit =	model.getDebit();
			boolean value1 = credit.isEmpty();
			boolean value2 = debit.isEmpty();
			if (value2 & value1==true) {
				response.sendRedirect("/BankApplication/fail.html");
				
			}
			else {
			session.setAttribute("credit", credit);
			session.setAttribute("debit", debit);
			response.sendRedirect("/BankApplication/statment.jsp");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}}

}
