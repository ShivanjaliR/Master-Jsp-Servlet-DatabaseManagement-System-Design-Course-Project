import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String selectedvalues[] = request.getParameterValues("addtocart");
		try {
			HttpSession hs=request.getSession();
			String user_username = (String) hs.getAttribute("username");
			out.println("<body bgcolor='#0000FF'>");
			out.println("<h1>Username:"+user_username+"<h1>");
			hs.setAttribute("username", user_username);
			
			int cid=(Integer)hs.getAttribute("cid");
			out.println("<h1>CID:"+cid+"<h1>");
			hs.setAttribute("cid",cid);
				
		if(selectedvalues !=null)
		{
			out.println("<h1>Your Selected Iteams</h1>");
			out.println("<h1>Please Select your Iteams and enter quantities</h1>");
			out.print("<form action='ConfirmCart' method='post'>");
			for(int i=0;i<selectedvalues.length;i++)
			{
				LoginDAO o;
				o = new LoginDAO();
				ResultSet rs=o.SelectedProduct(selectedvalues[i]);
				while(rs.next())
				{
					out.println("<br><br>");
					out.println("<table border='2'>");
					out.println("<tr>");
					out.println("<td></td>");
					out.println("<td>Product ID</td>");
					out.println("<td>Product Name</td>");
					out.println("<td>Price</td>");
					out.println("<td>Description</td>");
					out.println("<td>Quntaties</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td><input type='checkbox' name='confirmcart' value='"+rs.getInt("ProductID")+"'></td>");
					out.println("<td>"+rs.getInt("ProductID")+"</td>");
					out.println("<td>"+rs.getString("ProductName")+"</td>");
					out.println("<td>"+rs.getFloat("Price")+"</td>");
					out.println("<td>"+rs.getString("Description")+"</td><br>");
					out.println("<td><input type='textbox' name='qty"+selectedvalues[i]+"'></td>");
				}//end of while
			}//end of for
			out.println("<tr><td><input type='submit' value='Select'/></td></tr></table>");
		  }//end of if
		  else
		  {
			 out.println("Please add iteams into cart");
		  }//end of else
		}//end of try
		
		catch (InstantiationException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}//end of catch 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}//end of catch
	
	}//end of dopost

}
