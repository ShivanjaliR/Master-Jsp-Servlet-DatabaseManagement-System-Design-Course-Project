import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Bill extends HttpServlet 
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer ProdId[]=new Integer[10];
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	    
		HttpSession hs=request.getSession();
	    String user_username = (String)hs.getAttribute("username");
	    String existing_address= (String)hs.getAttribute("Exiting_Address");
	    out.print("cart No:"+hs.getAttribute("CartId"));
	    hs.setAttribute("Cart_Id",hs.getAttribute("CartId"));
	    hs.setAttribute("Exiting_Address",existing_address);
	    out.println("Existing Address:"+existing_address);
	      
	    		
	    int cid=(Integer)hs.getAttribute("cid");
	    hs.setAttribute("username", user_username);
	    hs.setAttribute("cid",cid);
	    out.println(cid);
	    
	    
	    String SA="";
	    String RecepientName="";
	    String Street="";
		String City="";
		String zip="";
		String state="";
		String country="";
		
		
		if(existing_address.equals("Yes"))
	    {
	    	SA=(String)hs.getAttribute("SAName");
		    hs.setAttribute("SAName",SA);
		    RecepientName=(String)hs.getAttribute("RecepientName");
		    hs.setAttribute("RecepientName",RecepientName);
			Street=(String)hs.getAttribute("Street");
			hs.setAttribute("Street",Street);
			City=(String)hs.getAttribute("City");
			hs.setAttribute("City",City);
			zip=(String)hs.getAttribute("Zip");
			hs.setAttribute("zip",zip);
			state=(String)hs.getAttribute("state");
			hs.setAttribute("state",state);
			country=(String)hs.getAttribute("country");
			hs.setAttribute("country",country);
				
	    }
	    else
	    {	    	
	    	SA=(String)request.getAttribute("SAName");
		    hs.setAttribute("SAName",SA);
		    RecepientName=(String)request.getAttribute("RecepientName");
		    hs.setAttribute("RecepientName",RecepientName);
			Street=(String)request.getAttribute("Street");
			hs.setAttribute("Street",Street);
			City=(String)request.getAttribute("City");
			hs.setAttribute("City",City);
			zip=(String)request.getAttribute("Zip");
			hs.setAttribute("zip",zip);
			state=(String)request.getAttribute("state");
			hs.setAttribute("state",state);
			country=(String)request.getAttribute("country");
			hs.setAttribute("country",country);
				
	    }
		out.println("SA:"+SA+"\nRecepientName"+RecepientName+"\nzip:"+zip);	
		 String selectedvalues[] = request.getParameterValues("finalorder");
		if(selectedvalues !=null)
		{
		    for(int i=0;i<selectedvalues.length;i++)
		    {
		    	ProdId[i]=Integer.parseInt(selectedvalues[i]);
		        out.println(selectedvalues[i]);    	
		        hs.setAttribute("Prod"+i,ProdId[i]);
		    	
			 
		    }//end of for
		}//end of if
		
		hs.setAttribute("NoOfProducts",selectedvalues.length);
		out.println("No of iteams :"+ selectedvalues.length);
    	try
    	{
    	Class.forName("com.mysql.jdbc.Driver");
    	String url="jdbc:mysql://localhost:3306/";
    	String username="root";
    	String password="root";
    	String dbname="online_shopping";
    	Connection con=DriverManager.getConnection(url+dbname,username,password);
    	PreparedStatement prep=con.prepareStatement("select * from creditcard where CardNo In (select CardNo from storedcard where CID=?)");
    	prep.setInt(1,cid);
    	ResultSet rs=prep.executeQuery();
//    

    	out.println("<h1>Select Card Number from Stored Card List from below</h1>");
    	 out.println("<form method=\"post\" name=\"billcredit\" action=\"/Online_Shopping/transcreditcard.jsp\">");
    	 out.println("<table border='2'>");
    	 out.println("<tr><td></td><td>Card No</td><td>Owner Name</td><td>Security Number</td><td>Card Type</td><td>Expiry Date</td><td>Address</td>");
 		while(rs.next())
    	{
 			 out.println("<tr>");  
      		 out.println("<td><input type='radio' name='bill' value="+rs.getInt("CardNo")+"></td>");
    		 out.println("<td>"+rs.getInt("CardNo")+"</td>");
    		 out.println("<td>"+rs.getString("OwnerName")+"</td>");
    		 out.println("<td>"+rs.getInt("SecNo")+"</td>");
    		 out.println("<td>"+rs.getString("CardType")+"</td>");
    		 out.println("<td>"+rs.getString("ExpDate")+"</td>");
    		 out.println("<td>"+rs.getString("Address")+"</td>");
    		 out.println("</tr>");  
        			
    	}//end of while
 		 out.println("</table>");
 		 out.println("<h1>If you want to add new card Information Please press Next button</h1>");
    	 out.println("<input type='submit' value='Next' name='Skip'></form>");
    	}//end of try
    	
    	catch(Exception e)
    	{
    		 
    	}//end of catch

	}//end of doPost

}
