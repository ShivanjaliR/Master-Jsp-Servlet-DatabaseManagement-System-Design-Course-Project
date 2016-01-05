

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddSA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String OwnerName="";
		String RecepientName="";
		String Street="";
		String City="";
		int Zip=0;
		String State="";
		String Country="";
	
		HttpSession hs=request.getSession();
		String username = (String)hs.getAttribute("username");
		hs.setAttribute("username", username);
		int cid=(Integer)hs.getAttribute("cid");
		hs.setAttribute("cid",cid);
		Boolean transaction = (Boolean)hs.getAttribute("transaction");
        if(transaction!=null)
        {
           if(transaction==true)
           {
             OwnerName=  (String) hs.getAttribute("SAName");
    	     RecepientName=(String) hs.getAttribute("RecepientName");
    	     Street= (String) hs.getAttribute("Street");
    	     City= (String) hs.getAttribute("City");
    	     Zip=Integer.parseInt( (String) hs.getAttribute("Zip"));
    	     State=(String) hs.getAttribute("State");
    	     Country= (String) hs.getAttribute("Country");
    	     transaction=false;
           }//end of inner if
        }
        else
        {
        	 OwnerName= request.getParameter("SAName");
    		 RecepientName= request.getParameter("RecepientName");
    		 Street= request.getParameter("Street");
    		 City= request.getParameter("City");
    		 Zip=Integer.parseInt( request.getParameter("Zip"));
    		 State= request.getParameter("State");
    		 Country= request.getParameter("Country");
    	
        }
		
	
		LoginDAO o;
		try 
		{
			o = new LoginDAO();
			boolean result = o.addSA(cid,OwnerName, RecepientName, Street, City, Zip, State,Country);
			if(result==true)
			{
				System.out.println("Successfully shipping address is set");
				out.println("Successful shipping address is set");
			}
			else
			{
				System.out.println("Un-Successfully shipping address is added");
				out.println("Un-Successful  shipping address is added");
			}
		}//end of try
	    catch (Exception e)
		{
			e.printStackTrace();
		}//end of catch
		
	}//end of method

}
