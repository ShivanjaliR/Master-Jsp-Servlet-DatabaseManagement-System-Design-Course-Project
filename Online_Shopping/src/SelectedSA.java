

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SelectedSA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		out.println("Hello");
        //HttpSession hs=request.getSession();
//		int cid=(Integer)request.getAttribute("existingSA");
//		out.println("<h1>"+cid+"<h1>");
//		hs.setAttribute("cid",cid);
		String SA=(String) request.getAttribute("SAName");
		out.println("<h1>"+SA+"</h1>");
	
	}

}
