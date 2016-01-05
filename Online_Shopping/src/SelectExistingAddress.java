import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SelectExistingAddress extends HttpServlet {
	public static int flag=0;
    private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<form method=\"post\" name=\"finalorder\" action=\"/Online_Shopping/finalorder.jsp\">");
			out.println("<table border='2'><tr><td></td><td>Shipping Address Name</td><td>Recepient Name</td><td>Street</td><td>City</td><td>Zip</td>");
			out.println("<td>State</td><td>Country</td></tr>");
			HttpSession hs=request.getSession();
			String user_username = (String)hs.getAttribute("username");
			int cid=(Integer)hs.getAttribute("cid");
			hs.setAttribute("username", user_username);
			hs.setAttribute("cid",cid);
			LoginDAO o = new LoginDAO();
			ResultSet rs=o.CIDShippingAddress(cid);
			try
			{				
			while(rs.next())
			{
			    out.println("<tr>");
			    out.println("<td><input type='radio' name='existingSA' value="+rs.getString("SAName")+">");
			    out.println("</td><td><input type='text' value='"+rs.getString("SAName")+"' name='SAName'></td>");	
				hs.setAttribute("SAName",rs.getString("SAName"));
				out.println("<td>"+rs.getString("RecepientName")+"</td><td>"+rs.getString("Street")+"</td>");
			    out.println("<td>"+rs.getString("City")+"</td><td>"+rs.getInt("Zip")+"</td>");
			    out.println("<td>"+rs.getString("State")+"</td><td>"+rs.getString("Country")+"</td></tr>"); 
			}//end of while
		     out.println("</table><input type='submit' value='Set'/></form>");
		     rs.close();
		     flag=1;
		     hs.setAttribute("flag",flag);
		    }//end of try
			catch(Exception e)
			{
			   e.printStackTrace();
			}//end of catch
		
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
			
	}//end of doPost

}//end of class
