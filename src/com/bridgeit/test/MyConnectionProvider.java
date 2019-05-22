package com.bridgeit.test;

import java.sql.DriverManager;

import java.sql.Connection;

public class MyConnectionProvider implements MyProvider
{
	static String userName="root";
	static String password="admIn@111";
	static String ConnectionUrl="jdbc:mysql://localhost:3306/LoginRegistration1";
	public static Connection con=null;
	
  public static Connection getCon()
  {
	  try
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  System.out.println("for name called");
		  System.out.println("userName :"+userName +" password :"+password);
		  con= DriverManager.getConnection(ConnectionUrl,userName,password);
		  System.out.println("server connected");
		 
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
	return con;
	  
  }
}
