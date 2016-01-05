import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResultSet rs=null;
    public ConfirmCart() {
        super();
    }
	
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
    	String selectedvalues[] = request.getParameterValues("confirmcart");
    	String prod_name=null,description=null;
    	int prodid=0;
    	float cost=0;
    	String qtyorder = null;
    	PrintWriter out=response.getWriter();
    	HttpSession hs=request.getSession();
		String user_username = (String) hs.getAttribute("username");
	
		int cid=(Integer)hs.getAttribute("cid");
		out.println("<body bgcolor='#0000FF'>");
			out.println("<h1>"+cid+"<h1>");
		hs.setAttribute("cid",cid);
		
		out.println("<h1>Your confirmed items..Your current cart status</h1>");
		out.println("<h1>"+user_username+"</h1>");
		out.print("<form>");
		try
		{
		  for(int i=0;i<selectedvalues.length;i++)
		  {
			LoginDAO o;
			o = new LoginDAO();
			rs=o.SelectedProduct(selectedvalues[i]);
			while(rs.next())
			{
		      qtyorder=request.getParameter("qty"+selectedvalues[i]+"");
			  prod_name=rs.getString("ProductName");
			  description=rs.getString("Description");
			  cost=rs.getFloat("Price");
		  	}//end of while
 		    o.addCart(Integer.parseInt(selectedvalues[i]),prod_name,cost,description,qtyorder,cid);
		  }//end of for
		  LoginDAO o=new LoginDAO();
		  ResultSet rs=o.getCart(cid);
		  while(rs.next())
		  {
		   	    out.println("<br><br>");
				out.println("<table border='2'>");
				out.println("<tr>");
				out.println("<td>Product ID</td>");
				out.println("<td>Product Name</td>");
				out.println("<td>Price</td>");
				out.println("<td>Description</td>");
				out.println("<td>Order Quantities</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>"+rs.getInt("ProdID")+"</td>");
				out.println("<td>"+prod_name+"</td>");
				out.println("<td>"+cost+"</td>");
				out.println("<td>"+description+"</td><br>");
				out.println("<td>"+qtyorder+"</td>");
	    }//end of while
	    rs.close();
		}//end of try
		catch(Exception e)
		{
			e.printStackTrace();
		}//end of catch
		 out.println("<br><br><a href='listproduct.jsp'>Back to the list</a>");
			
     }//end of method	
}//end of class		
		
		
		
		
		



