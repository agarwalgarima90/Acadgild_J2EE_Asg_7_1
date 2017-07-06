package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateServlet
 */
@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		  pw.println("Please enter below values to Update.");
		  pw.println("<form action='updateServlet' method='post'>");
		  pw.println("TITLE: <input type='text' name='title'/></br>");
		  pw.println("AUTHOR: <input type='text' name='author'/></br>");
		  pw.println("PUBLISHER: <input type='text' name='pub'/></br>");
		  pw.println("PUBLISH YEAR: <input type='text' name='year'/></br>");
		  pw.println("PRICE: <input type='text' name='price'/></br>");
		  pw.println("<input type='submit' name='update'/></form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		Connection connection = (Connection)getServletContext().getAttribute("database.connection");
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String pub = request.getParameter("pub");
		String year = request.getParameter("year");
		String price = request.getParameter("price");
		//Execute SQL query
        try {
        	Statement stmt = connection.createStatement();
        	String sql;
        	String query = "update books set title='" + title + "', author = '" + author +"', publisher = '" + pub + "', publication_year = '" +year + "', price = '" + price + "' " + "where title='" + title + "'";
        	
        	int result = stmt.executeUpdate(query);
        	if(result == 1) {
        		pw.print("Successfully Updated. ");
        	} else {
        		pw.print("No Such book.");
        	}
        } catch (Exception e) {
        	pw.println(e);
        }
	}

}
