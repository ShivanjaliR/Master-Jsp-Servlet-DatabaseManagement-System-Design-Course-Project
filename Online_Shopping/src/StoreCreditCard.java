import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StoreCreditCard extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("I am in StoredCreditCard");
		PrintWriter out=response.getWriter();
		HttpSession hs=request.getSession();
		String user_username = (String)hs.getAttribute("username");
		int cid=(Integer)hs.getAttribute("cid");
		hs.setAttribute("username", user_username);
		hs.setAttribute("cid",cid);
		String existing_address= (String)hs.getAttribute("Exiting_Address");
		hs.setAttribute("existing_address",existing_address);

		String cardno= request.getParameter("CardNo");
		String OwnerName= request.getParameter("OwnerName");
		String SecNo= request.getParameter("SecNo");
		String CardType= request.getParameter("CardType");
		
		String ExpDate= request.getParameter("ExpDate");
		String Address= request.getParameter("Address");
		out.println(cardno);
		
		LoginDAO o;
		try 
		{
//			o = new LoginDAO();
//			boolean result =o.storeCreditCard(cardno, OwnerName, SecNo, CardType, ExpDate, Address);
//			if(result==true)
//			{
//				System.out.println("Successful Stored Card in transaction level");
//				out.println("Successful Stored Card in transaction level");
//			}
//			else
//			{
//				System.out.println("Un-Successful Stored Card");
//				out.println("Un-Successful Stored Card");
//			}
//		    result=o.storeCC(cardno,cid);
//		    if(result==true)
//		    {
//		    	System.out.println("Successful Stored Card in account as well");
//				out.println("Successful Stored Card in account as well");
//		    }
//		    else
//		    {
//				System.out.println("Un-Successful Stored Card");
//				out.println("Un-Successful Stored Card");
//		    }
		}//end of try
	    catch (Exception e)
		{
			e.printStackTrace();
		}//end of catch
   }//end of doPost

}//end of class
