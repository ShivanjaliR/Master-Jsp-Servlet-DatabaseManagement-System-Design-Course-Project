import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String ProdType= request.getParameter("ProdType");
		String ProdName= request.getParameter("ProdName");
		float price= Float.parseFloat(request.getParameter("ProdPrice"));
		String Description= request.getParameter("ProdDes");
		int Quantity= Integer.parseInt(request.getParameter("ProdQunt"));
		LoginDAO o;
		try 
		{
			o = new LoginDAO();
			boolean result = o.addProduct(ProdType,ProdName,price,Description,Quantity);
			if(result==true)
			{
				System.out.println("Successfully Add Product in the list");
				out.println("Successfully Add Product in the list");
			}
			else
			{
				System.out.println("Un-Successfully to add product");
				out.println("Un-Successfully to add product...Try again");
			}
		}//end of try
	    catch (Exception e)
		{
			e.printStackTrace();
		}
    }//end of doPost

}//end of class


