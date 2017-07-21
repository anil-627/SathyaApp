package com.pack.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.pack.beans.UserDetails;

public class RegistrationDAOImpl implements RegistrationDAO {
	static final  Logger  logger=Logger.getLogger(RegistrationDAOImpl.class);
	private  Connection  con=null;
	public  RegistrationDAOImpl() throws  RuntimeException
	{
		logger.debug("entered into constructor of dao");
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
		}
		catch(Exception e)
		{
			logger.error("Exception in dao  due  to  : "+e.getMessage());
			throw  new  RuntimeException("");
		}
		logger.debug("exit from constructor of dao");
	}

	public void addUserDetails(UserDetails user) throws RuntimeException {
		logger.debug("entered into  adduserdetails() of dao");
		String sql="insert into  userdetails values(?,?,?,?)";
		PreparedStatement pstmt=null;
		try
		{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,user.getEmail());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getMobile());
			pstmt.setString(4, user.getLocation());
			pstmt.executeUpdate();
			logger.info("User details are inserted into Database successfully for : "+user.getEmail());
		}
		catch(Exception e)
		{
			logger.error("Error while inserting user details : "+e.getMessage());
			
		}
		logger.debug("exit from  addUserDetails() of dao");

	}

}
