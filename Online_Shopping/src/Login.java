

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String username= request.getParameter("username");
		String password= request.getParameter("userpass");
		HttpSession hs=request.getSession();
		hs.setAttribute("username", username);
	
		try 
		{
			
		RequestDispatcher rd=null;
		if(LoginDAO.validate(username,password))
		{
			LoginDAO o=new LoginDAO();
			int cid=o.getCID(username,password);	
			hs=request.getSession();
			hs.setAttribute("cid",cid);
			rd=request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);
	     }
		 else
		 {
			//out.print("Username and password is incorrect");
		    //rd.include(request,response);
			
		    rd=request.getRequestDispatcher("Sorry.jsp");
			rd.forward(request, response);
	
		  }//end of else
		}//end of try
		catch (InstantiationException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}//end of catch
	
		
		out.close();

		
	}

}
