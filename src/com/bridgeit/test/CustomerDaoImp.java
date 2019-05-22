package com.bridgeit.test;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDaoImp implements CustomerDao {
	static Connection con;
	static PreparedStatement ps;

	public int insertCustomer(Customer c) {
		int status = 0;
		try {
			System.out.println("data inserting into database");
			con = MyConnectionProvider.getCon();
			System.out.println("connection established");

			ps = con.prepareStatement("INSERT INTO LogReg VALUES(?,?,?,?,?,?)");

			ps.setString(1, c.getUser_Id());
			ps.setString(2, c.getFirst_Name());
			ps.setString(3, c.getLast_Name());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getPassword());
			ps.setString(6, c.getConform_Password());
			status = ps.executeUpdate();
			System.out.println("query executed");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;

	}

	public Customer getCustomer(String User_Id, String First_Name, String Last_Name, String Email, String Password,
			String Conform_Password) {
		Customer c = new Customer();
		try {
			System.out.println("getting info from database");
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("SELECT * FROM LogReg WHERE User_Id=? AND Password=?");
			ps.setString(1, User_Id);
			ps.setString(2, Password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c.setUser_Id(rs.getString(1));
				c.setFirst_Name(rs.getString(2));
				c.setLast_Name(rs.getString(3));
				c.setEmail(rs.getString(4));
				c.setPassword(rs.getString(5));
				c.setConform_Password(rs.getString(6));

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return c;

	}

	@Override
	public Customer forgot(String User_Id) {
		Customer c = new Customer();
		boolean login = true;
		try {
			System.out.println("getting info from database");
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("SELECT Password FROM LogReg");
			// ps.setString(1,Email);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if ((User_Id.equals(rs.getString(1))))
					;
				{
					System.out.println("login successfull...");
					login = false;
				}
				login = true;

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public int insert(String User_Id, String Password) {
		int status = 0;
		try {
			System.out.println("getting info from database");
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("UPDATE LogReg set Password=? WHERE User_Id=?");
			ps.setString(1, User_Id);
			ps.setString(2, Password);

			status = ps.executeUpdate();
			System.out.println("insert password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
