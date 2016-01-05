import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreditCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreditCard() {
        super();
    }
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   	{
   		
   		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession hs=request.getSession();
		String user_username = (String)hs.getAttribute("username");
		int cid=(Integer)hs.getAttribute("cid");
		hs.setAttribute("username", user_username);
		hs.setAttribute("cid",cid);

		int cardno= Integer.parseInt(request.getParameter("CardNo"));
		String OwnerName= request.getParameter("OwnerName");
		int SecNo= Integer.parseInt(request.getParameter("SecNo"));
		String CardType= request.getParameter("CardType");
		
		String ExpDate= request.getParameter("ExpDate");
		String Address= request.getParameter("Address");
		out.println("<body bgcolor='#0000FF'>");
		out.println("<a href='welcome.jsp'>Home</a>");
		LoginDAO o;
		try 
		{
			o = new LoginDAO();
			boolean result =o.storeCreditCard(cardno, OwnerName, SecNo, CardType, ExpDate, Address);
			if(result==true)
			{
				System.out.println("Successful Stored Card");
				out.println("<h1>you Successful Stored Card</h1>");
			}
			else
			{
				System.out.println("Un-Successful Stored Card");
				out.println("Un-Successful Stored Card");
			}
			String selectedvalues[] = request.getParameterValues("storeornot");
			if(selectedvalues!=null)
			{
				LoginDAO o1 = new LoginDAO();
				
			    result=o1.storeCC(cardno,cid);
			    if(result==true)
			    {
			    	System.out.println("Successful Stored Card in account as well");
					out.println("Successful Stored Card in account as well");
		    }
			    else
			    {
					System.out.println("Un-Successful Stored Card");
					out.println("Un-Successful Stored Card");
			    }
			
			}//end of if
			out.println("</body>");
		}//end of try
	    catch (Exception e)
		{
			e.printStackTrace();
		}//end of catch
   	}//end of doPost

}//end of CreditCard
