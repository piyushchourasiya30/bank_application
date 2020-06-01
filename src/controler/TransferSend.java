package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

/**
 * Servlet implementation class TransferSend
 */
public class TransferSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Model model = new Model();
		session = request.getSession();
		int accno = (int) session.getAttribute("accno");
		model.setAccno(accno);
		int amount = (Integer.parseInt(request.getParameter("amt")));
		int reaccno = (Integer.parseInt(request.getParameter("revacno")));

		try {
			boolean value = model.transfer1(amount);
			if (value == true) {
				boolean value2 = model.transfer2(reaccno, amount);
				if (value2) {

					response.sendRedirect("/BankApplication/transfersucces.html");

				} else {
					response.sendRedirect("/BankApplication/transferfail.html");
				}
			} else {

				response.sendRedirect("/BankApplication/transferfail.html");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
