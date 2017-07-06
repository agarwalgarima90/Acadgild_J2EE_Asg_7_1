package com.book;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * Servlet implementation class servlet1
 */
@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public servlet1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String bookName = request.getParameter("bname");
		String action = request.getParameter("action");
		
		// JDBC driver name and database URL
	      final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://localhost/onlinebooks";
	      
	  //  Database credentials
	      final String USER = "root";
	      final String PASS = "root123";
	      boolean found = false;
	      
	      try {
	    	// Register JDBC driver
	          Class.forName(JDBC_DRIVER);
	          Connection connection = (Connection)getServletContext().getAttribute("database.connection");
	          if (connection == null)
	          {
	                  connection = DriverManager.getConnection(DB_URL, USER, PASS);
	                  getServletContext().setAttribute("database.connection", connection);
	          }
	          
	       // Execute SQL query
	          if(action.equals("Find")) {
	        	  Statement stmt = connection.createStatement();
		          String sql;
		          sql = "SELECT * FROM books";
		          ResultSet rs = stmt.executeQuery(sql);
		          
		          while(rs.next()){
		              //Retrieve by column name
		              String title = rs.getString("title");
		              System.out.println("LOOK: " + rs.getString(1));

		        	  if(title.equals(bookName)) {
		        		  found = true;
		        		  pw.println("Title: " + rs.getString(1) + "</br>");
		        		  pw.println("Author: " + rs.getString(2) + "</br>");
		        		  pw.println("Publisher: " + rs.getString(3) + "</br>");
		        		  pw.println("Publish Year: " + rs.getString(4) + "</br>");
		        		  pw.println("Price: " + rs.getString(5) + "</br>");
		        		  break;
		        		  
		        	  } else {
		        		  found = false;
		        	  }
		           }
	          } else if (action.equals("Insert")){
	        	  RequestDispatcher rd = request.getRequestDispatcher("/insertServlet");
	        	  rd.forward(request, response);
	          } else if (action.equals("Delete")) {
	        	  RequestDispatcher rd = request.getRequestDispatcher("/deleteServlet");
	        	  rd.forward(request, response);
	          } else if (action.equals("Update")) {
	        	  RequestDispatcher rd = request.getRequestDispatcher("/updateServlet");
	        	  rd.forward(request, response);
	          }
	      } catch (Exception se) {
	    	//Handle errors for JDBC
	          se.printStackTrace();
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
