package controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class Login extends HttpServlet {
	private HttpSession session;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model = new Model();
		model.setUserid(request.getParameter("userid"));
		model.setPassword(request.getParameter("password"));
		
		
		try {
			boolean value= model.login();
			
			if(value==true)
			{
				session= request.getSession();
				session.setAttribute("accno", model.getAccno());
				System.out.println(session.getAttribute("accno"));
				response.sendRedirect("/BankApplication/home.html");
				
			}
			else
			{
				response.sendRedirect("/BankApplication/loginfail.html");
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}

