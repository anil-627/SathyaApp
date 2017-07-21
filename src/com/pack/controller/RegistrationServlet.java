package com.pack.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pack.beans.UserDetails;
import com.pack.dao.RegistrationDAO;
import com.pack.dao.RegistrationDAOImpl;


public class RegistrationServlet extends HttpServlet {
	
	static final Logger  logger=Logger.getLogger(RegistrationServlet.class);
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("enter into  doPost of servlet");
		PrintWriter out=response.getWriter();
		try
		{
			UserDetails user =new  UserDetails();
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setMobile(Integer.parseInt(request.getParameter("mobile").trim()));
			user.setLocation(request.getParameter("location"));
			RegistrationDAO  dao=new  RegistrationDAOImpl();
			dao.addUserDetails(user);
			out.println("Registration successfull");
			
		}
		catch(Exception e)
		{
			logger.error("Error in servlet due to : "+e.getMessage());
			out.println("Registration failed");
		}
		logger.debug("exit from  doPost of servlet");
	}
}
