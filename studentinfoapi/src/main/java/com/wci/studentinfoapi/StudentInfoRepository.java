package com.wci.studentinfoapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentInfoRepository {
	Connection con = null;
	List<StudentInfo>list=null;

    //INITIALIZING DATABASE CONNECTION	
	public StudentInfoRepository()

	{
		String url = "jdbc:mysql://localhost:3306/ wcis";
		String username = "root";
		String password = "saibaba330";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//GET ALL STUDENT
	public List<StudentInfo> getStudent()
	{
		list=new ArrayList<StudentInfo>();
		String sql="select*from studentinfo";
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				StudentInfo si=new StudentInfo();
				si.setName(rs.getString("sname"));
				si.setAge(rs.getInt("age"));
				si.setCourse(rs.getString("course"));
				si.setMobileNo(rs.getString("mobile"));
				si.setCity(rs.getString("city"));
				list.add(si);
				System.out.println(list);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
		
	}
	
	//GET ONE STUDENT BY ID
	public StudentInfo getParticularStudent(String mobile)
	{
		String sql="select*from studentinfo where mobile=?";
		StudentInfo sf=new StudentInfo();
		try
		{
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, mobile);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			sf.setName(rs.getString("sname"));
			sf.setAge(rs.getInt("age"));
			sf.setCourse(rs.getString("course"));
			sf.setMobileNo(rs.getString("mobile"));
			sf.setCity(rs.getString("city"));
			sf.setId(rs.getInt("id"));
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sf;
		
	}
	
	//POSTING THE DATA
	public StudentInfo createNewStudent(StudentInfo s)
	{
		String sql="insert into studentinfo(sname,age,course,mobile,city)values(?,?,?,?,?)";
		try
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setInt(2, s.getAge());
			ps.setString(3, s.getCourse());
			ps.setString(4, s.getMobileNo());
			ps.setString(5, s.getCity());
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	
	//UPDATING THE DATA
	
	public void updateStudent(StudentInfo s)
	{
		String sql="update studentinfo set sname=?,age=?,course=?,mobile=?,city=? where mobile=?";
		try
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setInt(2, s.getAge());
			ps.setString(3, s.getCourse());
			ps.setString(4, s.getMobileNo());
			ps.setString(5, s.getCity());
			ps.setString(6, s.getMobileNo());
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	//DELETE THE DATA
	public void deleteStudent(String mobile)
	{
		String sql="delete from studentinfo where mobile=?";
		try
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, mobile);
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
