package com.bridgeit.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/MailSend")
public class MailSend extends HttpServlet {
	static Mailer mailer = new Mailer();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		String Email = request.getParameter("email");
	//	String submitType = request.getParameter("submit");
		System.out.println(Email);
	
			
		//	request.setAttribute("Successmessage", "Registration done please continue with login");
		boolean check = checkEmail(Email);
		if(check == true)
		{
			System.out.println("Email verified");
		}
		else
		{
			System.out.println("not verified");
		}
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
			System.out.println("reg successfully");
			HttpSession session = request.getSession();
		    session.setAttribute("email", Email);
		
	}
	
	public static boolean checkEmail(String email) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = MyConnectionProvider.getCon();
			ps = conn.prepareStatement("select * from LogReg  where Email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				 mailer.send("poojasparkle124@gmail.com","incorrect124",email,"link","http://localhost:8080/forgot/reset.jsp");
				rs.close();
				ps.close();
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return false;
	}

}
