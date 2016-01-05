import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String Name= request.getParameter("Name");
		String EId= request.getParameter("EId");
		int PhoneNo= Integer.parseInt(request.getParameter("PhoneNo"));
		String Address= request.getParameter("Address");
		String username= request.getParameter("Username");
		String password= request.getParameter("Password");
		LoginDAO o;
		try 
		{
			o = new LoginDAO();
			boolean result = o.register(Name, EId, PhoneNo, Address, username, password);
			if(result==true)
			{
				System.out.println("Successful Registration");
				out.println("Successful Registration");
			}
			else
			{
				System.out.println("Un-Successful registration");
				out.println("Un-Successful Registration");
			}
		}//end of try
	    catch (Exception e)
		{
			e.printStackTrace();
		}
    }//end of doPost
}//end of registration 
