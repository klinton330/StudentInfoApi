package com.wci.studentinfoapi;

import java.util.List;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

//http://localhost:8090/studentinfoapi/wci/student--GET
//http://localhost:8090/studentinfoapi/wci/student/get/{mobileno}--Get particular
//http://localhost:8090/studentinfoapi/wci/student/create---Create
//http://localhost:8090/studentinfoapi/wci/student/update---Update
//http://localhost:8090/studentinfoapi/wci/student/delete/{mobileno}-Delete

@Path("student")
public class StudentInfoResources {
	StudentInfoRepository repo=new StudentInfoRepository ();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<StudentInfo>getAll()
	{
		return repo.getStudent();
	}
	
	
	
	@GET
	@Path("get/{mobile}")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentInfo getParticularStudent(@PathParam("mobile") String mobile)
	{
		return repo.getParticularStudent(mobile);
	}
	
	
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentInfo createStudent(StudentInfo s)
	{
		if(repo.getParticularStudent(s.getMobileNo()).getId()!=0)
		{
			System.out.println("Mobile No is ALready entered");
			s.setErrorMessage("Mobile No is Already Found");
			s.setName("");
			s.setAge(0);
			s.setCity("");
			s.setCourse("");
			s.setMobileNo("");
		}
		else
		repo.createNewStudent(s);
		return s;
		
	}
	
	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentInfo updateStudent(StudentInfo s)
	{
		if(repo.getParticularStudent(s.getMobileNo()).getId()==0)
		{
			repo.createNewStudent(s);
		}
		else
		{
			repo.updateStudent(s);
		}
		return s;
	}
	
	@DELETE
	@Path("delete/{mobile}")
	@Produces(MediaType.APPLICATION_JSON)
	public StudentInfo deleteStudent(@PathParam("mobile")String MobileNo)
	{
		StudentInfo s=repo.getParticularStudent(MobileNo);
		if(s.getId()!=0)
		{
			repo.deleteStudent(MobileNo);
		}
		else
			System.out.println("No record found");
		return s;
	}
	

}
